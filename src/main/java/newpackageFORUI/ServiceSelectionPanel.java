package newpackageFORUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Cursor;
import java.util.logging.Logger;
import java.util.logging.Level;
import model.BookingData;
import javax.swing.ScrollPaneConstants;
import java.util.Date;
import java.util.concurrent.TimeUnit; // Needed for date calculation

public class ServiceSelectionPanel extends javax.swing.JFrame {
    
    //Decleration

    //Decleration

    private final BookingData bookingData;
    // START: Temporary variables to hold service data
    // Swimming Pool
    private int sp_guests_temp = 0;
    private int sp_pwdSenior_temp = 0;
    private int sp_days_temp = 0;
    // Gym
    private int gym_guests_temp = 0;
    private int gym_pwdSenior_temp = 0;
    private int gym_days_temp = 0;
    // Foot Spa
    private int fs_guests_temp = 0;
    private int fs_pwdSenior_temp = 0;
    private int fs_days_temp = 0;
    // Aroma Facial Massage
    private int afm_guests_temp = 0;
    private int afm_pwdSenior_temp = 0;
    private int afm_days_temp = 0;
    // Thai Massage
    private int tm_guests_temp = 0;
    private int tm_pwdSenior_temp = 0;
    private int tm_days_temp = 0;
    // END: Temporary variables to hold service data
    
    // START: Fields for validation constraints
    private final int maxTotalGuests;
    private final long maxNights;
    // END: Fields for validation constraints
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ServiceSelectionPanel.class.getName());
   
    public ServiceSelectionPanel(BookingData data) {
        this.bookingData = data;
        
        // START: Calculate constraints here using data's getters
        this.maxTotalGuests = data.getAdults() + data.getChildren();
        this.maxNights = calculateMaxNights(data.getCheckIn(), data.getCheckOut());
        // END: Calculate constraints
        
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
    }
    
    // DANIEL -> = = = START: New private method to calculate max number of nights = = =
    private long calculateMaxNights(Date checkIn, Date checkOut) {
        if (checkIn == null || checkOut == null) {
            return 0; // Cannot calculate if dates are missing
        }
        long diff = checkOut.getTime() - checkIn.getTime();
        // Convert milliseconds to days (number of nights)
        // TimeUnit.DAYS.convert handles the conversion safely
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    // DANIEL -> = = = END: New private method to calculate max number of nights = = =
    
    // --- Helper method for input dialog (MODIFIED to include validation) ---
    private String[] showServiceInputDialog(String serviceName) {
        
        // Input fields for the dialog
        JTextField guestField = new JTextField(5);
        JTextField pwdSeniorField = new JTextField(5);
        JTextField daysField = new JTextField(5);
        
        // Panel to hold the input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        
        // Display constraints
        inputPanel.add(new JLabel("Max Guests allowed: " + maxTotalGuests));
        inputPanel.add(new JLabel("Max Days allowed (Stay duration): " + maxNights));
        inputPanel.add(Box.createVerticalStrut(10)); // Add space
        
        inputPanel.add(new JLabel("No. Of Guests (non PWD/Senior):"));
        inputPanel.add(guestField);
        inputPanel.add(new JLabel("No. Of PWD/Senior Citizen:"));
        inputPanel.add(pwdSeniorField);
        inputPanel.add(new JLabel("No. Of Days:"));
        inputPanel.add(daysField);

        // Show the dialog
        int result = JOptionPane.showConfirmDialog(
            this, 
            inputPanel, 
            "Enter Details for " + serviceName, 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.PLAIN_MESSAGE);

        // Check if the user clicked Confirm (OK_OPTION)
        if (result == JOptionPane.OK_OPTION) {
            String guestsStr = guestField.getText().trim();
            String pwdSeniorStr = pwdSeniorField.getText().trim();
            String daysStr = daysField.getText().trim();

            // 1. Basic validation (NumberFormatException check)
            try {
                int guests = Integer.parseInt(guestsStr);
                int pwdSenior = Integer.parseInt(pwdSeniorStr);
                int days = Integer.parseInt(daysStr);
                
                // --- Validation 2a: Total Guests vs Max Total Guests ---
                int totalServiceGuests = guests + pwdSenior;
                if (totalServiceGuests > maxTotalGuests) {
                    JOptionPane.showMessageDialog(this, 
                        "Total Guests (" + totalServiceGuests + ") must not exceed the total number of people in the booking (" + maxTotalGuests + ").", 
                        "Input Error: Guest Count", JOptionPane.ERROR_MESSAGE);
                    return null; 
                }
                
                // --- Validation 2b: Days vs Max Nights (Stay Duration) ---
                if (days > maxNights) {
                    JOptionPane.showMessageDialog(this, 
                        "Number of Days (" + days + ") must not exceed the length of your stay (" + maxNights + " nights).", 
                        "Input Error: Day Count", JOptionPane.ERROR_MESSAGE);
                    return null; 
                }
                
                // --- Validation 2c: Ensure non-negative numbers ---
                 if (guests < 0 || pwdSenior < 0 || days < 0) {
                    JOptionPane.showMessageDialog(this, "All input fields must be non-negative numbers.", "Input Error: Negative Value", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
                
                // 3. All validation passed.
                return new String[]{guestsStr, pwdSeniorStr, daysStr};
            } catch (NumberFormatException e) {
                // Log and show error if input is not a valid number
                logger.log(Level.WARNING, "Invalid number input for service details: " + e.getMessage());
                JOptionPane.showMessageDialog(this, "Please enter valid integers for all fields.", "Input Error: Format", JOptionPane.ERROR_MESSAGE);
                return null; // Return null to indicate failure/cancellation
            }
        }
        return null; // Return null if the user cancelled the dialog
    }

    // --- Helper method to update the JTextArea ---
    private void updateServiceText(JTextArea textArea, String guests, String pwdSenior, String days) {
        textArea.setText(
            "No. Of Guests: " + guests + "\n" +
            "No. Of PWD/Senior Citizen: " + pwdSenior + "\n" +
            "No. Of Days: " + days
        );
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        AromaFacialMassage = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        FacialMassageText = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        FootSpa = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        FootSpaText = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        ThaiMassage = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        ThaiMassageText = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Gym = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        GymText = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        SwimmingPool = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        SwimmingPoolText = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SELECT SERVICES");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 760, 50));

        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, 110, 40));

        confirmButton.setText("CONFIRM");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        getContentPane().add(confirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 400, 110, 40));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(null);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(null);
        jPanel5.add(jPanel6);
        jPanel6.setBounds(0, 0, 0, 0);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Amenities/Services");
        jPanel5.add(jLabel2);
        jLabel2.setBounds(0, 0, 260, 40);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setText(" • Swimming Pool: \nPhp 300.00/day per person\n• Gym Php: \n500.00/day per person\n\nSPA:\n• Foot Spa:\nPhp 825.00 for 45 minutes\n\n• Aroma Facial Massage:\nPhp 1,045.00 for 45 minutes\n\n• Thai Massage:\nPhp 1,540.00 for 75 minutes");
        jTextArea3.setBorder(null);
        jTextArea3.setFocusable(false);
        jScrollPane3.setViewportView(jTextArea3);

        jPanel5.add(jScrollPane3);
        jScrollPane3.setBounds(10, 40, 240, 240);

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 260, 290));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Spa"));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        AromaFacialMassage.setText("Facial Massage");
        AromaFacialMassage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        AromaFacialMassage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AromaFacialMassageActionPerformed(evt);
            }
        });

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane6.setHorizontalScrollBar(null);
        jScrollPane6.setWheelScrollingEnabled(false);
        jScrollPane6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        FacialMassageText.setColumns(20);
        FacialMassageText.setRows(5);
        FacialMassageText.setText("No. Of Guests:\nNo. Of PWD/Senior Citizen:\nNo. Of Days:");
        FacialMassageText.setBorder(null);
        FacialMassageText.setFocusable(false);
        jScrollPane6.setViewportView(FacialMassageText);
        jScrollPane6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(AromaFacialMassage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(AromaFacialMassage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        FootSpa.setText("Foot Spa");
        FootSpa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        FootSpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FootSpaActionPerformed(evt);
            }
        });

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane5.setHorizontalScrollBar(null);
        jScrollPane5.setWheelScrollingEnabled(false);

        FootSpaText.setColumns(20);
        FootSpaText.setRows(5);
        FootSpaText.setText("No. Of Guests:\nNo. Of PWD/Senior Citizen:\nNo. Of Days:");
        FootSpaText.setBorder(null);
        FootSpaText.setFocusable(false);
        jScrollPane5.setViewportView(FootSpaText);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(FootSpa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 92, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(FootSpa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ThaiMassage.setText("Thai Massage");
        ThaiMassage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ThaiMassage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThaiMassageActionPerformed(evt);
            }
        });

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane7.setHorizontalScrollBar(null);
        jScrollPane7.setWheelScrollingEnabled(false);

        ThaiMassageText.setColumns(20);
        ThaiMassageText.setRows(5);
        ThaiMassageText.setText("No. Of Guests:\nNo. Of PWD/Senior Citizen:\nNo. Of Days:");
        ThaiMassageText.setBorder(null);
        ThaiMassageText.setFocusable(false);
        jScrollPane7.setViewportView(ThaiMassageText);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(ThaiMassage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 90, Short.MAX_VALUE))
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(ThaiMassage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 220, 350));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Gym"));
        jPanel9.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Gym.setText("Gym");
        Gym.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Gym.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GymActionPerformed(evt);
            }
        });

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane4.setHorizontalScrollBar(null);
        jScrollPane4.setWheelScrollingEnabled(false);

        GymText.setColumns(20);
        GymText.setRows(5);
        GymText.setText("No. Of Guests:\nNo. Of PWD/Senior Citizen:\nNo. Of Days:");
        GymText.setBorder(null);
        GymText.setFocusable(false);
        jScrollPane4.setViewportView(GymText);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Gym, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 88, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Gym, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel2);
        jPanel2.setBounds(10, 30, 190, 100);

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 210, 150));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Swimming Pool"));
        jPanel10.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        SwimmingPool.setText("Swimming Pool");
        SwimmingPool.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        SwimmingPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwimmingPoolActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBar(null);
        jScrollPane2.setWheelScrollingEnabled(false);

        SwimmingPoolText.setColumns(20);
        SwimmingPoolText.setRows(5);
        SwimmingPoolText.setText("No. Of Guests:\nNo. Of PWD/Senior Citizen:\nNo. Of Days:");
        SwimmingPoolText.setBorder(null);
        SwimmingPoolText.setFocusable(false);
        jScrollPane2.setViewportView(SwimmingPoolText);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(SwimmingPool, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 88, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(SwimmingPool, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel1);
        jPanel1.setBounds(10, 30, 190, 100);

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 210, 140));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
int result = JOptionPane.showConfirmDialog(this, 
            "Confirm? Are all these information correct?", 
            "Confirmation", 
            JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            
            // START: Transfer all temporary service data to the BookingData object
            // Swimming Pool
            bookingData.setSp_guests(sp_guests_temp);
            bookingData.setSp_pwdSenior(sp_pwdSenior_temp);
            bookingData.setSp_days(sp_days_temp);
            // Gym
            bookingData.setGym_guests(gym_guests_temp);
            bookingData.setGym_pwdSenior(gym_pwdSenior_temp);
            bookingData.setGym_days(gym_days_temp);
            // Foot Spa
            bookingData.setFs_guests(fs_guests_temp);
            bookingData.setFs_pwdSenior(fs_pwdSenior_temp);
            bookingData.setFs_days(fs_days_temp);
            // Aroma Facial Massage
            bookingData.setAfm_guests(afm_guests_temp);
            bookingData.setAfm_pwdSenior(afm_pwdSenior_temp);
            bookingData.setAfm_days(afm_days_temp);
            // Thai Massage
            bookingData.setTm_guests(tm_guests_temp);
            bookingData.setTm_pwdSenior(tm_pwdSenior_temp);
            bookingData.setTm_days(tm_days_temp);
            // END: Transfer all temporary service data
            
            // proceed to PaymentPanel
            // NOTE: Assuming PaymentPanelMainMenu exists and is accessible.
            try {
                // Use reflection to create an instance, as the class might not be defined in this file.
                Class<?> paymentClass = Class.forName("newpackageFORUI.PaymentPanelMainMenu");
                JFrame payment = (JFrame) paymentClass.getDeclaredConstructor().newInstance();
                payment.setVisible(true);
                this.dispose(); // close current window
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Could not instantiate PaymentPanelMainMenu", e);
                JOptionPane.showMessageDialog(this, "Error: Could not load the next panel.", "System Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
         int result = JOptionPane.showConfirmDialog(this,
        "Confirm? Are you sure you want to go back to Addon Selection?",
        "Back Confirmation",
        JOptionPane.YES_NO_OPTION);

    if (result == JOptionPane.YES_OPTION) {
        // clear all fields (not strictly necessary here as data is in bookingData, but good practice if inputs were complex)

        // NOTE: Assuming AddonSelectionPanel exists and is accessible.
        try {
            Class<?> addonClass = Class.forName("newpackageFORUI.AddonSelectionPanel");
            // Assuming AddonSelectionPanel needs BookingData in its constructor, like this panel.
            // If the original AddonSelectionPanel() constructor worked, we'll keep that assumption.
            // If it needs BookingData: addon.getDeclaredConstructor(BookingData.class).newInstance(bookingData);
            JFrame addon = (JFrame) addonClass.getDeclaredConstructor().newInstance(); 
            addon.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Could not instantiate AddonSelectionPanel", e);
            JOptionPane.showMessageDialog(this, "Error: Could not load the previous panel.", "System Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // if NO, do nothing
    }//GEN-LAST:event_backButtonActionPerformed

    private void SwimmingPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SwimmingPoolActionPerformed
       String[] inputs = showServiceInputDialog("Swimming Pool");
        if (inputs != null) {
            updateServiceText(SwimmingPoolText, inputs[0], inputs[1], inputs[2]);
            // Save to temporary variables
            sp_guests_temp = Integer.parseInt(inputs[0]);
            sp_pwdSenior_temp = Integer.parseInt(inputs[1]);
            sp_days_temp = Integer.parseInt(inputs[2]);
        }
    }//GEN-LAST:event_SwimmingPoolActionPerformed

    private void GymActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GymActionPerformed
        String[] inputs = showServiceInputDialog("Gym");
        if (inputs != null) {
            updateServiceText(GymText, inputs[0], inputs[1], inputs[2]);
            // Save to temporary variables
            gym_guests_temp = Integer.parseInt(inputs[0]);
            gym_pwdSenior_temp = Integer.parseInt(inputs[1]);
            gym_days_temp = Integer.parseInt(inputs[2]);
        }
    }//GEN-LAST:event_GymActionPerformed

    private void FootSpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FootSpaActionPerformed
      String[] inputs = showServiceInputDialog("Foot Spa");
        if (inputs != null) {
            updateServiceText(FootSpaText, inputs[0], inputs[1], inputs[2]);
            // Save to temporary variables
            fs_guests_temp = Integer.parseInt(inputs[0]);
            fs_pwdSenior_temp = Integer.parseInt(inputs[1]);
            fs_days_temp = Integer.parseInt(inputs[2]);
        }
    }//GEN-LAST:event_FootSpaActionPerformed

    private void AromaFacialMassageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AromaFacialMassageActionPerformed
       String[] inputs = showServiceInputDialog("Aroma Facial Massage");
        if (inputs != null) {
            updateServiceText(FacialMassageText, inputs[0], inputs[1], inputs[2]);
            // Save to temporary variables
            afm_guests_temp = Integer.parseInt(inputs[0]);
            afm_pwdSenior_temp = Integer.parseInt(inputs[1]);
            afm_days_temp = Integer.parseInt(inputs[2]);
        }
    }//GEN-LAST:event_AromaFacialMassageActionPerformed

    private void ThaiMassageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThaiMassageActionPerformed
       String[] inputs = showServiceInputDialog("Thai Massage");
        if (inputs != null) {
            updateServiceText(ThaiMassageText, inputs[0], inputs[1], inputs[2]);
            // Save to temporary variables
            tm_guests_temp = Integer.parseInt(inputs[0]);
            tm_pwdSenior_temp = Integer.parseInt(inputs[1]);
            tm_days_temp = Integer.parseInt(inputs[2]);
        }
    }//GEN-LAST:event_ThaiMassageActionPerformed

 
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
        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AromaFacialMassage;
    private javax.swing.JTextArea FacialMassageText;
    private javax.swing.JButton FootSpa;
    private javax.swing.JTextArea FootSpaText;
    private javax.swing.JButton Gym;
    private javax.swing.JTextArea GymText;
    private javax.swing.JButton SwimmingPool;
    private javax.swing.JTextArea SwimmingPoolText;
    private javax.swing.JButton ThaiMassage;
    private javax.swing.JTextArea ThaiMassageText;
    private javax.swing.JButton backButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables
}