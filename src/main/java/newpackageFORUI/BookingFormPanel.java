/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package newpackageFORUI;
import com.db.DBConnection;
import com.toedter.calendar.JCalendar;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



import model.BookingData;


import java.time.ZoneId; // Needed for date conversion
import java.time.LocalDate; // Needed for date math

/**
 *
 * @author Lenovo
 */
public class BookingFormPanel extends javax.swing.JFrame {
    String[] destinationTypes = {"Local", "International"};

String[] localDestinations = {
    "Baguio",
    "Boracay",
    "El Nido",
    "Siargao"
};

String[] internationalDestinations = {
    "Hong Kong",
    "Japan",
    "Singapore",
    "South Korea"
};

    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BookingFormPanel.class.getName());
    private BookingData bookingData = new BookingData();

    
    
    
    
    
    
    
    
    
    
    /**
     * Creates new form BookingFormPanel
     */
    public BookingFormPanel() {
          initComponents();
          // ADD LISTENER TO AUTO-LOAD DESTINATIONS
    // =====================================
    // 1. CLEAR COMBOBOXES FIRST
    // =====================================
    destination_type_picker.removeAllItems();
    destination_picker.removeAllItems();

    // =====================================
    // 2. ADD BLANK DEFAULT ITEM
    // =====================================
    destination_type_picker.addItem("");   // blank first item
    destination_picker.addItem("");        // blank first item

    // =====================================
    // 3. ADD DESTINATION TYPES BELOW THE BLANK ITEM
    // =====================================
    for (String type : destinationTypes) {
        destination_type_picker.addItem(type);
    }

    // =====================================
    // 4. ADD LISTENER (AFTER POPULATING TYPES)
    // =====================================
    destination_type_picker.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            // If user selects blank (“”), reset destination picker
            if (destination_type_picker.getSelectedIndex() == 0) {
                destination_picker.removeAllItems();
                destination_picker.addItem("");   // keep blank
                return;
            }

            loadDestinationsBasedOnType();
        }
    });
    
          
    
    // --- START OF NEW 18+ AGE RESTRICTION ---
    // 1. Calculate the required age threshold (18 years ago)
    java.time.LocalDate today = java.time.LocalDate.now();
    java.time.LocalDate requiredAgeThreshold = today.minusYears(18);
    
    // 2. Convert LocalDate (java.time) back to Date (java.util) for JDateChooser
    java.util.Date maxSelectableDate = java.util.Date.from(
        requiredAgeThreshold.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()
    );
    
    // 3. Apply the maximum date restriction to the birth date picker
    Booker_birthdate.setMaxSelectableDate(maxSelectableDate);
    // --- END OF NEW 18+ AGE RESTRICTION ---

    // Setting layout for dynamic age inputs (from previous step)
    input_ages_box.setLayout(new java.awt.GridLayout(0, 2, 5, 5)); 
    
    Howmanychildren_picker.setSelectedIndex(0); // set default children to 0
    
    // Update children age boxes on startup
    updateAgeInputs(); 
    
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    
    // ===== Rule 1: Check-in must be after today =====
    jDatechooseCheckin.setMinSelectableDate(new java.util.Date(today.toEpochDay() * 24*60*60*1000 + 24*60*60*1000)); // tomorrow

    // ===== Rule 2: Check-out must be after check-in =====
    jDatechooseCheckin.addPropertyChangeListener("date", evt -> {
        java.util.Date checkInDate = jDatechooseCheckin.getDate();
        if (checkInDate != null) {
            jDateChoosecheckout.setMinSelectableDate(new java.util.Date(checkInDate.getTime() + 24*60*60*1000)); // at least 1 day after check-in

            java.util.Date currentCheckOut = jDateChoosecheckout.getDate();
            if (currentCheckOut != null && !currentCheckOut.after(checkInDate)) {
                jDateChoosecheckout.setDate(null);
            }
        }
    });
       
    } 
    private void updateAgeInputs() {
        // 1. Get number of children selected
        String selectedStr = (String) Howmanychildren_picker.getSelectedItem();
        int count = 0;
        try {
            count = Integer.parseInt(selectedStr);
        } catch (NumberFormatException e) {
            count = 0;
        }

        // 2. Clear previous inputs
        input_ages_box.removeAll();
        
        // 3. Create new inputs based on count
        // Using GridLayout: Rows = count, Cols = 2 (Label + Input)
        input_ages_box.setLayout(new java.awt.GridLayout(count, 2, 5, 5));
        
        for (int i = 1; i <= count; i++) {
            JLabel lbl = new JLabel("Child " + i + ":");
            JTextField txt = new JTextField(3); // width of 3 columns
            input_ages_box.add(lbl);
            input_ages_box.add(txt);
        }

        // 4. Refresh the UI
        input_ages_box.revalidate();
        input_ages_box.repaint();
        this.pack(); // Resize window to fit new inputs
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupOne = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Booker_name = new javax.swing.JTextField();
        Booker_birthdate = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        Booker_email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        booker_mobileno = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        Howmanyadults_picker = new javax.swing.JComboBox<>();
        Howmanychildren_picker = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        input_ages_box = new javax.swing.JPanel();
        Previous_page = new javax.swing.JButton();
        Confirm = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        destination_type_picker = new javax.swing.JComboBox<>();
        destination_picker = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jDatechooseCheckin = new com.toedter.calendar.JDateChooser();
        jDateChoosecheckout = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 30)); // NOI18N
        jLabel1.setText("LANLYA HOTEL RESERVATION SYSTEM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel2.setText("GUEST INFORMATION");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(26, 26, 26))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setText("Name:");

        Booker_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Booker_nameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("Birthdate:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel5.setText("Email:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Mobile no.");

        booker_mobileno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                booker_mobilenoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Booker_birthdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Booker_email))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Booker_name, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(booker_mobileno)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Booker_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Booker_birthdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Booker_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(booker_mobileno, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Howmanyadults_picker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));

        Howmanychildren_picker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));
        Howmanychildren_picker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Howmanychildren_pickerActionPerformed(evt);
            }
        });

        jLabel7.setText("Adults");

        jLabel8.setText("Children");

        input_ages_box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout input_ages_boxLayout = new javax.swing.GroupLayout(input_ages_box);
        input_ages_box.setLayout(input_ages_boxLayout);
        input_ages_boxLayout.setHorizontalGroup(
            input_ages_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        input_ages_boxLayout.setVerticalGroup(
            input_ages_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Howmanyadults_picker, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Howmanychildren_picker, 0, 88, Short.MAX_VALUE)))
                .addGap(47, 47, 47)
                .addComponent(input_ages_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Howmanyadults_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Howmanychildren_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input_ages_box, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Previous_page.setText("Back");

        Confirm.setText("Confirm");
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel9.setText("BOOKING FORM");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(22, 22, 22))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("Destination type:");

        destination_type_picker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        destination_type_picker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destination_type_pickerActionPerformed(evt);
            }
        });

        destination_picker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Destination:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(destination_type_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(destination_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destination_type_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destination_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("CHECK-IN");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("CHECK-OUT");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(87, 87, 87))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jDatechooseCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDateChoosecheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDatechooseCheckin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChoosecheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(165, 165, 165))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Previous_page, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Previous_page, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void booker_mobilenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_booker_mobilenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_booker_mobilenoActionPerformed

    private void destination_type_pickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destination_type_pickerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_destination_type_pickerActionPerformed

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        
       // ============ REQUIRED FIELD VALIDATIONS ============

    // --- NAME ---
    String name = Booker_name.getText().trim();
    if (name.isEmpty() || !name.matches("^[A-Za-z ]+$")) {
        JOptionPane.showMessageDialog(this, "Please enter a valid name (letters only).");
        return;
    }

    // --- BIRTHDATE ---
    java.util.Date birthdate = Booker_birthdate.getDate();
    if (birthdate == null) {
        JOptionPane.showMessageDialog(this, "Please select your birthdate.");
        return;
    }

    // Additional age check (in case user bypasses the min date restriction)
    LocalDate bday = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate today = LocalDate.now();
    if (bday.isAfter(today.minusYears(18))) {
        JOptionPane.showMessageDialog(this, "You must be at least 18 years old.");
        return;
    }

    // --- EMAIL ---
    String email = Booker_email.getText().trim();
    if (email.isEmpty() || !email.matches("^[^@]+@[^@]+\\.[^@]+$")) {
        JOptionPane.showMessageDialog(this, "Please enter a valid email.");
        return;
    }

    // --- MOBILE NUMBER ---
    String mobile = booker_mobileno.getText().trim();
    if (mobile.isEmpty() || !mobile.matches("\\d{11}")) {
        JOptionPane.showMessageDialog(this, "Mobile number must be 11 digits.");
        return;
    }

    // --- ADULTS ---
    int adults = Integer.parseInt((String) Howmanyadults_picker.getSelectedItem());
    if (adults <= 0) {
        JOptionPane.showMessageDialog(this, "Please select number of adults.");
        return;
    }

    // --- CHILDREN COUNT ---
    int children = Integer.parseInt((String) Howmanychildren_picker.getSelectedItem());
    if (children < 0) {
        JOptionPane.showMessageDialog(this, "Please select number of children.");
        return;
    }

    // --- CHILDREN AGES ---
    if (children > 0) {
        for (int i = 0; i < input_ages_box.getComponentCount(); i += 2) {
            JTextField txt = (JTextField) input_ages_box.getComponent(i + 1);
            String ageStr = txt.getText().trim();

            if (ageStr.isEmpty() || !ageStr.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Please enter valid age for each child.");
                return;
            }

            int age = Integer.parseInt(ageStr);
            if (age < 0 || age > 17) {
                JOptionPane.showMessageDialog(this, "Child ages must be 0–17.");
                return;
            }
        }
    }

    // --- DESTINATION TYPE ---
    if (destination_type_picker.getSelectedItem() == null ||
        destination_type_picker.getSelectedItem().toString().isEmpty()) {

        JOptionPane.showMessageDialog(this, "Please select a destination type.");
        return;
    }

    // --- DESTINATION ---
    if (destination_picker.getSelectedItem() == null ||
        destination_picker.getSelectedItem().toString().isEmpty()) {

        JOptionPane.showMessageDialog(this, "Please select a destination.");
        return;
    }

    // --- CHECK-IN DATE ---
    java.util.Date checkIn = jDatechooseCheckin.getDate();
    if (checkIn == null) {
        JOptionPane.showMessageDialog(this, "Please select a check-in date.");
        return;
    }

    // --- CHECK-OUT DATE ---
    java.util.Date checkOut = jDateChoosecheckout.getDate();
    if (checkOut == null) {
        JOptionPane.showMessageDialog(this, "Please select a check-out date.");
        return;
    }

    if (!checkOut.after(checkIn)) {
        JOptionPane.showMessageDialog(this, "Check-out must be after check-in.");
        return;
    }

    // =====================================================
    // If code reaches here → ALL INPUTS ARE VALID
    // Continue with database saving logic below
    // =====================================================

    // =====================================================
// If code reaches here → ALL INPUTS ARE VALID
// Ask user for final confirmation
// =====================================================

int result = JOptionPane.showConfirmDialog(
        this,
        "Are all inputs correct?",
        "Confirm Inputs",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
);

if (result == JOptionPane.YES_OPTION) {
    // User clicked YES → proceed with database saving
    JOptionPane.showMessageDialog(this, "Saving to database...");
    // TODO: add your database save logic here
    
    // --- REDIRECT TO ANOTHER FORM ---
    RoomSelectionPanel roomSelection = new RoomSelectionPanel(); // instantiate next form
    roomSelection.setVisible(true); // show RoomSelectionPanel
    this.dispose(); // close current BookingFormPanel
} else {
    // User clicked NO → cancel
    JOptionPane.showMessageDialog(this, "Please review your inputs.");
    return; // stop further execution
}

        
    }//GEN-LAST:event_ConfirmActionPerformed

    private void Booker_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Booker_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Booker_nameActionPerformed

    private void Howmanychildren_pickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Howmanychildren_pickerActionPerformed
        // TODO add your handling code here:
        updateAgeInputs();
    }//GEN-LAST:event_Howmanychildren_pickerActionPerformed

    
    

    
    


    
    
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new BookingFormPanel().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Booker_birthdate;
    private javax.swing.JTextField Booker_email;
    private javax.swing.JTextField Booker_name;
    private javax.swing.JButton Confirm;
    private javax.swing.JComboBox<String> Howmanyadults_picker;
    private javax.swing.JComboBox<String> Howmanychildren_picker;
    private javax.swing.JButton Previous_page;
    private javax.swing.JTextField booker_mobileno;
    private javax.swing.ButtonGroup buttonGroupOne;
    private javax.swing.JComboBox<String> destination_picker;
    private javax.swing.JComboBox<String> destination_type_picker;
    private javax.swing.JPanel input_ages_box;
    private com.toedter.calendar.JDateChooser jDateChoosecheckout;
    private com.toedter.calendar.JDateChooser jDatechooseCheckin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables

  private void generateAgeFields() {
     input_ages_box.removeAll(); // clear previous fields

    int numAdults = Howmanyadults_picker.getSelectedIndex();
    int numChildren = Howmanychildren_picker.getSelectedIndex();

    // Adults
    for (int i = 1; i <= numAdults; i++) {
        input_ages_box.add(new javax.swing.JLabel("Adult " + i + " Age:"));
        input_ages_box.add(new javax.swing.JTextField());
    }

    // Children
    for (int i = 1; i <= numChildren; i++) {
        input_ages_box.add(new javax.swing.JLabel("Child " + i + " Age:"));
        input_ages_box.add(new javax.swing.JTextField());
    }

    input_ages_box.revalidate(); // refresh layout
    input_ages_box.repaint();
}
  
  
  
  
  private void loadDestinationsBasedOnType() {
    destination_picker.removeAllItems();

    String selectedType = (String) destination_type_picker.getSelectedItem();

    if (selectedType == null) return;

    if (selectedType.equals("Local")) {
        for (String loc : localDestinations) {
            destination_picker.addItem(loc);
        }
    } else if (selectedType.equals("International")) {
        for (String intl : internationalDestinations) {
            destination_picker.addItem(intl);
        }
    }
}


  
}
