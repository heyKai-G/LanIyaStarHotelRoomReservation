package newpackageFORUI;

import model.BookingData;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.JTextArea; // Explicit import
import javax.swing.JScrollPane; // Explicit import


/**
 *
 * @author Lenovo
 */
public class BookingSummaryPanel extends javax.swing.JFrame {
    
    private static final Logger logger = Logger.getLogger(BookingSummaryPanel.class.getName());
    private BookingData bookingData;
    private static final double PWD_SENIOR_DISCOUNT = 0.20; // 20% discount

    /**
     * Creates new form BookingSummaryPanel
     */
    // New constructor to accept BookingData
    public BookingSummaryPanel(BookingData data) {
        this.bookingData = data;
        initComponents();
        // Call a method to populate the summary text upon creation
        updateSummaryText();
        Color bgColor = Color.decode("#c6af81");
        this.getContentPane().setBackground(bgColor);
    }
    
    // Default constructor for design/main method, kept for compilation but should be updated if used in main flow
    public BookingSummaryPanel() {
        initComponents();
        setLocationRelativeTo(null);
        Color bgColor = Color.decode("#c6af81");
        this.getContentPane().setBackground(bgColor);
    }
    
    // Helper method to calculate the number of days between check-in and check-out
    private long calculateTotalDays() {
        Date checkIn = bookingData.getCheckIn();
        Date checkOut = bookingData.getCheckOut();
        if (checkIn == null || checkOut == null) {
            return 0;
        }
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    
    // Method to get the Room Name based on ID
    private String getRoomName(String id) {
        return switch (id) {
            case "1" -> "Standard";
            case "2" -> "Deluxe";
            case "3" -> "Quadruple";
            case "4" -> "Family";
            case "5" -> "Suite";
            default -> "Unknown Room";
        };
    }
    
    // Method to determine the base rate for the room
    private double getDailyRoomRate(String selectedRateColumn) {
        String roomId = bookingData.getSelectedRoomIdInput();
        double baseRate;
        switch (roomId) {
            case "1": baseRate = 2500.00; break; // Standard
            case "2": baseRate = 3500.00; break; // Deluxe
            case "3": baseRate = 5000.00; break; // Quadruple
            case "4": baseRate = 7500.00; break; // Family
            case "5": baseRate = 12000.00; break; // Suite
            default: baseRate = 0.00; break;
        }
        
        // Hypothetical rate adjustment based on selectedRateColumn (Season)
        if (selectedRateColumn != null && selectedRateColumn.contains("Peak")) {
            baseRate *= 1.2; // 20% increase for Peak Season
        }
        
        return baseRate;
    }
    
    // Method to calculate the total cost of Add-ons
    private double calculateAddOnsTotal() {
        double total = 0.0;
        
        // Bed: Php 650.00/night * numBeds * numNightsBed
        total += bookingData.getNumBeds() * bookingData.getNumNightsBed() * 650.00;
        
        // Blanket: Php 250.00 * numBlankets
        total += bookingData.getNumBlankets() * 250.00;
        
        // Pillow: Php 100.00 * numPillows
        total += bookingData.getNumPillows() * 100.00;
        
        // Toiletries: Php 200.00 * numToiletries
        total += bookingData.getNumToiletries() * 200.00;
        
        return total;
    }
    
    // Method to calculate the total cost of a single service
    private double calculateServiceCost(int guests, int pwdSenior, int days, double rate) {
        // PWD/Senior Discount: 20%
        int regularGuests = guests;
        int pwdSeniorGuests = pwdSenior;
        
        // Calculate cost for regular guests
        double regularCost = regularGuests * days * rate;
        
        // Calculate cost for PWD/Senior guests with discount
        double discountedRate = rate * (1.0 - PWD_SENIOR_DISCOUNT);
        double pwdSeniorCost = pwdSeniorGuests * days * discountedRate;
        
        return regularCost + pwdSeniorCost;
    }
    
    // Method to calculate the total cost of all Services
    private double calculateServicesTotal() {
        double total = 0.0;
        
        // --- Pool (Php 300.00/day per person) ---
        total += calculateServiceCost(
            bookingData.getSp_guests(), 
            bookingData.getSp_pwdSenior(), 
            bookingData.getSp_days(), 
            300.00
        );
        
        // --- Gym (Php 500.00/day per person) ---
        total += calculateServiceCost(
            bookingData.getGym_guests(), 
            bookingData.getGym_pwdSenior(), 
            bookingData.getGym_days(), 
            500.00
        );
        
        // --- Foot Spa (Php 825.00 for 45 minutes) ---
        double fs_rate = 825.00;
        double fs_regCost = bookingData.getFs_guests() * bookingData.getFs_days() * fs_rate;
        double fs_pwdCost = bookingData.getFs_pwdSenior() * bookingData.getFs_days() * (fs_rate * (1.0 - PWD_SENIOR_DISCOUNT));
        total += fs_regCost + fs_pwdCost;

        // Aroma Facial Massage (Php 1,045.00 for 45 minutes)
        double afm_rate = 1045.00;
        double afm_regCost = bookingData.getAfm_guests() * bookingData.getAfm_days() * afm_rate;
        double afm_pwdCost = bookingData.getAfm_pwdSenior() * bookingData.getAfm_days() * (afm_rate * (1.0 - PWD_SENIOR_DISCOUNT));
        total += afm_regCost + afm_pwdCost;
        
        // Thai Massage (Php 1,540.00 for 75 minutes)
        double tm_rate = 1540.00;
        double tm_regCost = bookingData.getTm_guests() * bookingData.getTm_days() * tm_rate;
        double tm_pwdCost = bookingData.getTm_pwdSenior() * bookingData.getTm_days() * (tm_rate * (1.0 - PWD_SENIOR_DISCOUNT));
        total += tm_regCost + tm_pwdCost;
        
        return total;
    }

    // Main method to generate and display the receipt
    public void updateSummaryText() {
        if (bookingData == null) {
            summaryTextArea.setText("Booking data is missing.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        long totalDays = calculateTotalDays();
        double dailyRate = getDailyRoomRate(bookingData.getSelectedRateColumn());
        double roomTotal = dailyRate * totalDays;
        double addOnsTotal = calculateAddOnsTotal();
        double servicesTotal = calculateServicesTotal();
        double grandTotal = roomTotal + addOnsTotal + servicesTotal;
        
        bookingData.setGrandTotal(grandTotal);
        StringBuilder summary = new StringBuilder();
        
        // HEADER
        summary.append("========================================================================================\n");
        summary.append(String.format("%70s\n", "BOOKING RECEIPT SUMMARY"));
        summary.append("========================================================================================\n\n");
        
        // FIRST: Booker Info
        summary.append("--- BOOKER & TRAVEL INFORMATION ---\n");
        summary.append(String.format("Booker Name: %s\n", bookingData.getBookerName()));
        summary.append(String.format("Birthdate: %s\n", dateFormat.format(bookingData.getBirthdate())));
        summary.append(String.format("Email: %s\n", bookingData.getEmail()));
        summary.append(String.format("Mobile: %s\n", bookingData.getMobile()));
        summary.append(String.format("Guests: %d Adult(s), %d Child(ren)\n", bookingData.getAdults(), bookingData.getChildren()));
        summary.append(String.format("Destination: %s (%s)\n", bookingData.getDestination(), bookingData.getDestinationType()));
        summary.append(String.format("Check-in: %s\n", dateFormat.format(bookingData.getCheckIn())));
        summary.append(String.format("Check-out: %s\n", dateFormat.format(bookingData.getCheckOut())));
        summary.append(String.format("Total Days/Nights: %d\n\n", totalDays));
        
        // SECOND: Room Info
        summary.append("--- ROOM CHARGES ---\n");
        String roomName = getRoomName(bookingData.getSelectedRoomIdInput());
        String rateType = bookingData.getSelectedRateColumn() != null ? bookingData.getSelectedRateColumn().replace("Rate", " Rate") : "N/A Rate";
        summary.append(String.format("Room: %s (ID: %s)\n", roomName, bookingData.getSelectedRoomIdInput()));
        summary.append(String.format("Season: %s\n", bookingData.getDeterminedSeason()));
        summary.append(String.format("Rate Type: %s\n", rateType));
        summary.append(String.format("Daily Rate: Php %,.2f\n", dailyRate));
        summary.append(String.format("Total Room Charge (%d days x Php %,.2f): Php %,.2f\n\n", totalDays, dailyRate, roomTotal));
        
        // THIRD: Add-ons
        summary.append("--- ADD-ONS ---\n");
        if (bookingData.getNumBeds() > 0) {
            double cost = bookingData.getNumBeds() * bookingData.getNumNightsBed() * 650.00;
            summary.append(String.format("Extra Beds: %d (Nights: %d) @ Php 650.00/night: Php %,.2f\n", 
                bookingData.getNumBeds(), bookingData.getNumNightsBed(), cost));
        }
        if (bookingData.getNumBlankets() > 0) {
            double cost = bookingData.getNumBlankets() * 250.00;
            summary.append(String.format("Extra Blankets: %d @ Php 250.00/unit: Php %,.2f\n", 
                bookingData.getNumBlankets(), cost));
        }
        if (bookingData.getNumPillows() > 0) {
            double cost = bookingData.getNumPillows() * 100.00;
            summary.append(String.format("Extra Pillows: %d @ Php 100.00/unit: Php %,.2f\n", 
                bookingData.getNumPillows(), cost));
        }
        if (bookingData.getNumToiletries() > 0) {
            double cost = bookingData.getNumToiletries() * 200.00;
            summary.append(String.format("Extra Toiletries: %d @ Php 200.00/unit: Php %,.2f\n", 
                bookingData.getNumToiletries(), cost));
        }
        summary.append(String.format("Total Add-ons Cost: Php %,.2f\n\n", addOnsTotal));
        
        // FOURTH: Services
        summary.append("--- SERVICES ---\n");
        if (bookingData.getSp_days() > 0) {
            double cost = calculateServiceCost(bookingData.getSp_guests(), bookingData.getSp_pwdSenior(), bookingData.getSp_days(), 300.00);
            summary.append(String.format("Swimming Pool (%d days): %d Guest(s), %d PWD/Senior @ Php 300.00/day: Php %,.2f\n", 
                bookingData.getSp_days(), bookingData.getSp_guests(), bookingData.getSp_pwdSenior(), cost));
        }
        if (bookingData.getGym_days() > 0) {
            double cost = calculateServiceCost(bookingData.getGym_guests(), bookingData.getGym_pwdSenior(), bookingData.getGym_days(), 500.00);
            summary.append(String.format("Gym (%d days): %d Guest(s), %d PWD/Senior @ Php 500.00/day: Php %,.2f\n", 
                bookingData.getGym_days(), bookingData.getGym_guests(), bookingData.getGym_pwdSenior(), cost));
        }
        // Spa Services (using the derived logic: PWD/Senior get 20% off)
        if (bookingData.getFs_days() > 0) {
            double regCost = bookingData.getFs_guests() * bookingData.getFs_days() * 825.00;
            double pwdCost = bookingData.getFs_pwdSenior() * bookingData.getFs_days() * (825.00 * (1.0 - PWD_SENIOR_DISCOUNT));
            summary.append(String.format("Foot Spa (%d sessions): %d Guest(s), %d PWD/Senior @ Php 825.00/session: Php %,.2f\n", 
                bookingData.getFs_days(), bookingData.getFs_guests(), bookingData.getFs_pwdSenior(), regCost + pwdCost));
        }
        if (bookingData.getAfm_days() > 0) {
            double regCost = bookingData.getAfm_guests() * bookingData.getAfm_days() * 1045.00;
            double pwdCost = bookingData.getAfm_pwdSenior() * bookingData.getAfm_days() * (1045.00 * (1.0 - PWD_SENIOR_DISCOUNT));
            summary.append(String.format("Aroma Facial Massage (%d sessions): %d Guest(s), %d PWD/Senior @ Php 1,045.00/session: Php %,.2f\n", 
                bookingData.getAfm_days(), bookingData.getAfm_guests(), bookingData.getAfm_pwdSenior(), regCost + pwdCost));
        }
        if (bookingData.getTm_days() > 0) {
            double regCost = bookingData.getTm_guests() * bookingData.getTm_days() * 1540.00;
            double pwdCost = bookingData.getTm_pwdSenior() * bookingData.getTm_days() * (1540.00 * (1.0 - PWD_SENIOR_DISCOUNT));
            summary.append(String.format("Thai Massage (%d sessions): %d Guest(s), %d PWD/Senior @ Php 1,540.00/session: Php %,.2f\n", 
                bookingData.getTm_days(), bookingData.getTm_guests(), bookingData.getTm_pwdSenior(), regCost + pwdCost));
        }
        summary.append(String.format("Total Services Cost: Php %,.2f\n\n", servicesTotal));
        
        // LAST: Grand Total
        summary.append("========================================================================================\n");
        summary.append(String.format("%69s\n", "SUMMARY OF CHARGES"));
        summary.append(String.format("Room Total: %60s %,.2f\n", "Php", roomTotal));
        summary.append(String.format("Add-ons Total: %57s %,.2f\n", "Php", addOnsTotal));
        summary.append(String.format("Services Total: %56s %,.2f\n", "Php", servicesTotal));
        summary.append("----------------------------------------------------------------------------------------\n");
        summary.append(String.format("**GRAND TOTAL PAYABLE:** %50s **%,.2f**\n", "**Php**", grandTotal));
        summary.append("========================================================================================\n");


        // Set the text in the SummaryText area (Line 167)
        summaryTextArea.setText(summary.toString());
        // Set caret position to the start of the text
        summaryTextArea.setCaretPosition(0);
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
        backButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        summaryTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("BOOKING SUMMARY");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 760, 50));

        backButton.setBackground(new java.awt.Color(198, 175, 129));
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 360, 100, 40));

        confirmButton.setBackground(new java.awt.Color(198, 175, 129));
        confirmButton.setText("CONFIRM");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        getContentPane().add(confirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 360, 100, 40));

        summaryTextArea.setColumns(20);
        summaryTextArea.setRows(5);
        summaryTextArea.setText("asgasgasg");
        jScrollPane1.setViewportView(summaryTextArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 760, 220));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

    }//GEN-LAST:event_backButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
 // 1. Hide the current window
        this.setVisible(false);
        
        // 2. Open the PaymentPanelMainMenu, passing the BookingData object
        PaymentPanelMainMenu paymentMenu = new PaymentPanelMainMenu(bookingData);
        paymentMenu.setVisible(true);
    }//GEN-LAST:event_confirmButtonActionPerformed

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

java.awt.EventQueue.invokeLater(() -> new BookingSummaryPanel().setVisible(true));
        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea summaryTextArea;
    // End of variables declaration//GEN-END:variables
}