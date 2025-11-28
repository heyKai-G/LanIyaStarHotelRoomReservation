/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package newpackageFORUI;

/**
 *
 * @author Lenovo
 */

import java.time.LocalDate;
import java.time.Month;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BookingData; // Import BookingData

// === NEW REGEX IMPORTS ===
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// =========================


public class RoomSelectionForGUESTLocal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RoomSelectionForGUESTLocal.class.getName());
    
    // --- NEW FIELD TO HOLD BOOKING DATA ---
    private final BookingData bookingData;
    private String selectedRateColumn; // To hold the determined rate column name
    private String determinedSeason;   // To hold the determined season name
    // --------------------------------------
    
    /**
     * Creates new form RoomSelectionForGUEST
     * // --- UPDATED CONSTRUCTOR ---
     */
    public RoomSelectionForGUESTLocal(BookingData data) {
        initComponents();
        this.bookingData = data; // Store the passed data
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        // --- ADD THIS LINE ---
        determineSeasonAndRateColumn(); // Determine season before loading data
        loadDataFromDatabaseLocal(); 
        suggestRoom(); // <<< NEW CALL: Suggest the room after loading data
        // ---------------------
    }
    
    // Original parameterless constructor is no longer safe to use, but kept for main compatibility
    public RoomSelectionForGUESTLocal() {
        // Safe fallback or use for design view, but should be avoided in production flow
        this(null); 
    }
    
    
    
    
    
    
    
    
    private boolean isDateBetween(int currentMonth, int currentDay, int m1, int d1, int m2, int d2) {
        // Check if the current date is exactly the start date
        if (currentMonth == m1 && currentDay >= d1 && currentMonth <= m2) {
            return true;
        }
        // Check if the current date is exactly the end date
        if (currentMonth == m2 && currentDay <= d2 && currentMonth >= m1) {
            return true;
        }
        // Check if the current date is between the start and end months
        if (currentMonth > m1 && currentMonth < m2) {
            return true;
        }
        // Handles cross-year ranges (like Dec 20 - Jan 5)
        if (m1 > m2) { 
            // If the range crosses the year end (e.g., Dec to Jan)
            return (currentMonth == m1 && currentDay >= d1) || 
                   (currentMonth == m2 && currentDay <= d2) || 
                   (currentMonth > m1 || currentMonth < m2); // Should not happen for Jan-Dec, but safe for cross-year
        }
        
        return false;
    }
 
    private void determineSeasonAndRateColumn() {
        if (bookingData == null || bookingData.getCheckIn() == null) {
            // Default to Lean if no date is provided
            this.determinedSeason = "N/A (Default to Lean)";
            this.selectedRateColumn = "rate_lean";
            return; 
        }

        // Convert java.util.Date to java.time.LocalDate for easier date comparison
        Date utilDate = bookingData.getCheckIn();
        LocalDate checkIn = utilDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        
        int month = checkIn.getMonthValue();
        int day = checkIn.getDayOfMonth();
        int year = checkIn.getYear();

        
        // === HOLY WEEK 2025: April 14 (Monday) to April 20 (Sunday) ===
        LocalDate hwStart = LocalDate.of(2025, 4, 14);
        LocalDate hwEnd = LocalDate.of(2025, 4, 20);

        // === CHINESE NEW YEAR 2025: January 28 (Eve) to January 29 (Day) ===
        LocalDate cnyStart = LocalDate.of(2025, 1, 28);
        LocalDate cnyEnd = LocalDate.of(2025, 1, 29);
        

        // === SUPER PEAK SEASON LOGIC ===
        
        // 1. December 20 to January 5 (Cross-year check)
        boolean isChristmasNewYear = (month == 12 && day >= 20) || (month == 1 && day <= 5);
        
        // 2. Holy Week 2025 (April 14-20)
        boolean isHolyWeek2025 = checkIn.isAfter(hwStart.minusDays(1)) && checkIn.isBefore(hwEnd.plusDays(1)) && year == 2025; 
        
        // 3. Chinese New Year 2025 (Jan 28-29)
        boolean isCNY2025 = checkIn.isAfter(cnyStart.minusDays(1)) && checkIn.isBefore(cnyEnd.plusDays(1)) && year == 2025;
        
        if (isChristmasNewYear || isHolyWeek2025 || isCNY2025)
        {
            this.determinedSeason = "Super Peak";
            this.selectedRateColumn = "rate_super_peak";
        } 
        
        // === PEAK SEASON LOGIC (March 1 to May 31) ===
        else if (isDateBetween(month, day, 3, 1, 5, 31)) {
            this.determinedSeason = "Peak";
            this.selectedRateColumn = "rate_peak";
        }
        
        // === HIGH SEASON LOGIC ===
        else if (isDateBetween(month, day, 11, 1, 12, 19) || // November 1 to December 19
                 isDateBetween(month, day, 1, 6, 2, 28))    // January 6 to February 28
        {
            this.determinedSeason = "High";
            this.selectedRateColumn = "rate_high";
        }
        
        // === LEAN SEASON LOGIC (June 1 to October 31) ===
        else if (isDateBetween(month, day, 6, 1, 10, 31)) {
            this.determinedSeason = "Lean";
            this.selectedRateColumn = "rate_lean";
        }
        
        // Fallback for any other date (shouldn't happen, but just in case)
        else {
            this.determinedSeason = "Season Not Defined (Default: Lean)";
            this.selectedRateColumn = "rate_lean";
        }
        
        // Update the label with the determined season
        if (bookingData != null && bookingData.getCheckIn() != null) {
            // Note: Assuming a JLabel named jLabel4 exists on the form
            jLabel4.setText("ROOM SELECTION LOCAL - " + this.determinedSeason.toUpperCase());
        }
    }
    
 
  private void loadDataFromDatabaseLocal() {
        // Ensure the rate column has been determined
        if (selectedRateColumn == null) {
            determineSeasonAndRateColumn();
        }
        
        String url = "jdbc:mysql://localhost:3306/hotel_reservation";  
        String user = "root";  
        String password = "ballislife2006"; 

        // Note: Assuming roomSelectionTableLocal is the JTable variable name
        if (roomSelectionTableLocal == null) {
            logger.log(Level.SEVERE, "roomSelectionTableLocal is null. Cannot load data.");
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) roomSelectionTableLocal.getModel();
        // Update the 6th column name to the specific season rate
        model.setColumnIdentifiers(new Object[]{"ID", "Destination Type", "Room Type", "Capacity", "Available Rooms", determinedSeason + " Price"});
        model.setRowCount(0); // clear existing rows

        // Use the dynamic rate column in the SQL query
        String sql = "SELECT id, destination_type, room_type, capacity, available_rooms, " + selectedRateColumn + " FROM roomrates_local";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Object[] row = new Object[6]; 
                
                row[0] = rs.getInt("id");                  
                row[1] = rs.getString("destination_type"); 
                row[2] = rs.getString("room_type");        
                row[3] = rs.getString("capacity");         
                row[4] = rs.getInt("available_rooms");     
                // --- NEW: Dynamic Rate Column ---
                row[5] = rs.getDouble(selectedRateColumn); // Pull the determined rate
                // --------------------------------
                
                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading Local data for Room Selection:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            logger.log(Level.SEVERE, "Database loading error (Local) in RoomSelection", e);
        }
    }
    
    
    /**
     * Determines and suggests the best room ID based on the total number of guests.
     * The logic is to find the smallest room capacity that is >= total guests.
     */
    private void suggestRoom() {
        // 1. Check for booking data presence
        if (bookingData == null) {
            roomNoSuggestText.setText("Booking data not available.");
            return;
        }
        
        // 2. Calculate total guests
        int totalGuests = bookingData.getAdults() + bookingData.getChildren();
        
        // 3. Check for guests presence
        if (totalGuests <= 0) {
            roomNoSuggestText.setText("No guests specified in booking.");
            return;
        }
        
        // **CONFIRMATION MESSAGE**: Display guest count to confirm input
        roomNoSuggestText.setText(String.format("Searching room for %d guest(s)...", totalGuests));
        
        // 4. Access table data
        DefaultTableModel model = (DefaultTableModel) roomSelectionTableLocal.getModel();
        int rowCount = model.getRowCount();
        
        if (rowCount == 0) {
            roomNoSuggestText.setText(String.format("No room data loaded for %d guests.", totalGuests));
            return;
        }
        
        // Regular expression pattern to match the first integer at the start of the string (ignoring leading whitespace)
        // Pattern: ^\\s*(\\d+)
        // ^\\s* -> start of string, followed by zero or more whitespace characters
        // (\\d+)    -> capture group 1: one or more digits
        Pattern pattern = Pattern.compile("^\\s*(\\d+)");
        
        // 5. Find the best matching room
        int bestRoomId = -1;
        int bestRoomCapacity = Integer.MAX_VALUE;
        String bestRoomType = "";
        
        for (int i = 0; i < rowCount; i++) {
            int capacity = 0; // Initialize capacity for this room
            try {
                // Assuming 'ID' is 0, 'Room Type' is 2, 'Capacity' is 3, 'Available Rooms' is 4
                
                String capacityStr = model.getValueAt(i, 3).toString();
                
                // === NEW CAPACITY EXTRACTION LOGIC ===
                Matcher matcher = pattern.matcher(capacityStr);
                
                if (matcher.find()) {
                    capacity = Integer.parseInt(matcher.group(1)); // Extract and parse the captured digits
                } else {
                    // If the capacity string doesn't start with a number, treat capacity as 0
                    logger.log(Level.WARNING, "Capacity string does not start with a number: " + capacityStr);
                    continue; // Skip this room
                }
                // =====================================
                
                int availableRooms = (int) model.getValueAt(i, 4); 
                int roomId = (int) model.getValueAt(i, 0); 
                String roomType = model.getValueAt(i, 2).toString();

                // Check if capacity is enough AND there are available rooms
                if (capacity >= totalGuests && availableRooms > 0) {
                    
                    // We want the room with the smallest sufficient capacity (best fit)
                    if (capacity < bestRoomCapacity) {
                        bestRoomCapacity = capacity;
                        bestRoomId = roomId;
                        bestRoomType = roomType;
                    }
                }
            } catch (NumberFormatException e) {
                 // Should be largely caught by the regex logic now, but keep for safety
                 logger.log(Level.SEVERE, 
                        String.format("Capacity parsing failed (unexpected error) for row %d. Capacity value: '%s'.", i, model.getValueAt(i, 3)), e);
            } catch (ClassCastException e) {
                 // Log a severe error if casting fails, but continue to the next room
                 logger.log(Level.SEVERE, 
                        String.format("Data type mismatch for row %d (Available Rooms or ID).", i), e);
            }
        }
        
        // 6. Display the result
        if (bestRoomId != -1) {
            String suggestion = String.format("Suggested Room Type: %s (ID: %d, Capacity: %d)", 
                                              bestRoomType, bestRoomId, bestRoomCapacity);
            roomNoSuggestText.setText(suggestion);
        } else {
            // Check if guests were too high for all rooms, or if all rooms were unavailable.
            roomNoSuggestText.setText(String.format("No available room found for %d guest(s). Please check table data (Capacity must start with a number, and Available Rooms > 0).", totalGuests));
        }
    }
    
    private boolean checkRoomAvailability(int roomId) {
        DefaultTableModel model = (DefaultTableModel) roomSelectionTableLocal.getModel();
        int rowCount = model.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            try {
                // Assuming ID is at index 0
                int tableRoomId = (int) model.getValueAt(i, 0); 
                
                if (tableRoomId == roomId) {
                    // Assuming Available Rooms is at index 4
                    int availableRooms = (int) model.getValueAt(i, 4); 

                    if (availableRooms > 0) {
                        // Room found and available
                        return true; 
                    } else {
                        // Room found but unavailable
                        JOptionPane.showMessageDialog(this, 
                            String.format("Room ID %d is currently not available (Available: %d).", roomId, availableRooms), 
                            "Room Unavailable", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                }
            } catch (ClassCastException | NullPointerException e) {
                logger.log(Level.SEVERE, "Error reading room data from table row " + i, e);
                JOptionPane.showMessageDialog(this, "Error reading room data from table.", "Data Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Room ID not found in the table
        JOptionPane.showMessageDialog(this, 
            String.format("Room ID %d does not exist in the available selection.", roomId), 
            "Invalid Room ID", JOptionPane.WARNING_MESSAGE);
        return false;
    }
 
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomSelectionTableLocal = new javax.swing.JTable();
        suggestedRoomLabel = new javax.swing.JTextArea();
        roomNoLabel = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        roomNoSuggestText = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        enterRoomInput = new javax.swing.JTextArea();
        Confirm = new javax.swing.JButton();
        Previous_page = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ROOM SELECTION LOCAL");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        roomSelectionTableLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Destination Type", "Room Type", "Capacity", "Available Rooms", "Price"
            }
        ));
        jScrollPane1.setViewportView(roomSelectionTableLocal);

        suggestedRoomLabel.setEditable(false);
        suggestedRoomLabel.setColumns(20);
        suggestedRoomLabel.setRows(5);
        suggestedRoomLabel.setText("Suggested Room No(s) based \non number of guests:");
        suggestedRoomLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        suggestedRoomLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        suggestedRoomLabel.setFocusable(false);

        roomNoLabel.setEditable(false);
        roomNoLabel.setColumns(20);
        roomNoLabel.setRows(5);
        roomNoLabel.setText("Enter Room No(s):");
        roomNoLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        roomNoLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        roomNoLabel.setFocusable(false);

        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        roomNoSuggestText.setEditable(false);
        roomNoSuggestText.setColumns(20);
        roomNoSuggestText.setRows(5);
        roomNoSuggestText.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        roomNoSuggestText.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        roomNoSuggestText.setFocusable(false);
        jScrollPane11.setViewportView(roomNoSuggestText);

        jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane12.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        enterRoomInput.setColumns(20);
        enterRoomInput.setRows(5);
        enterRoomInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        enterRoomInput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane12.setViewportView(enterRoomInput);

        Confirm.setText("Confirm");
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        Previous_page.setText("Back");
        Previous_page.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Previous_pageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(suggestedRoomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(roomNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Previous_page, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(suggestedRoomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roomNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Previous_page, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
       String roomInput = enterRoomInput.getText().trim();

if (roomInput.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter a Room ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
    return;
}

try {
    int selectedRoomId = Integer.parseInt(roomInput);

    // 1. Check if the room exists and is available
    if (checkRoomAvailability(selectedRoomId)) {
        // 2. Save Data
        bookingData.setSelectedRoomIdInput(roomInput);

        // 3. Confirmation Dialog
        int result = JOptionPane.showConfirmDialog(this,
            // Displaying the room ID for better user confirmation
            "You have selected Room ID: " + roomInput + ".\n\n"
            + "Confirm selection and proceed to Add-on Selection?",
            "Confirm Room Selection",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE); // QUESTION_MESSAGE is a suitable icon

        if (result == JOptionPane.YES_OPTION) {
            // 4. Transition to next panel (AddonSelectionPanel)
            try {
                // Original transition code wrapped here
                Class<?> addonClass = Class.forName("newpackageFORUI.AddonSelectionPanel");
                java.lang.reflect.Constructor<?> constructor = addonClass.getConstructor(model.BookingData.class);
                javax.swing.JFrame addonPanel = (javax.swing.JFrame) constructor.newInstance(bookingData);

                addonPanel.setVisible(true);
                this.dispose(); // close current window

            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this,
                    "Error: AddonSelectionPanel class not found. Please create this file.",
                    "Missing Class", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error creating/showing AddonSelectionPanel", e);
                JOptionPane.showMessageDialog(this, "Error starting next step.", "System Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        // If the user selects NO, the confirmation dialog simply closes,
        // and they remain on the current Room Selection screen.

    }
    // If checkRoomAvailability returns false, it already showed an error message.

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Invalid Room ID. Please enter a single whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_ConfirmActionPerformed

    private void Previous_pageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Previous_pageActionPerformed
        int result = JOptionPane.showConfirmDialog(this,
        "Confirm? Are you sure you want to go back to Booking Form?",
        "Back Confirmation",
        JOptionPane.YES_NO_OPTION);

    if (result == JOptionPane.YES_OPTION) {
        // clear all fields
        enterRoomInput.setText("");
        BookingFormPanel booking = new BookingFormPanel();
        booking.setVisible(true);
        this.dispose();
    }
    }//GEN-LAST:event_Previous_pageActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Confirm;
    private javax.swing.JButton Previous_page;
    private javax.swing.JTextArea enterRoomInput;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JTextArea roomNoLabel;
    private javax.swing.JTextArea roomNoSuggestText;
    private javax.swing.JTable roomSelectionTableLocal;
    private javax.swing.JTextArea suggestedRoomLabel;
    // End of variables declaration//GEN-END:variables
}
