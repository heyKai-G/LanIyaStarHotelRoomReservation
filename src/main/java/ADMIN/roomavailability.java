/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ADMIN;

/**
 *
 * @author Lenovo
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.TableModel;


import java.sql.*;
import javax.swing.table.DefaultTableModel;





import java.sql.*;
import java.util.logging.Level;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class roomavailability extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(roomavailability.class.getName());


    
    /**
     * Creates new form roomavailability
     */
    public roomavailability() {
        initComponents();
        
        loadDataFromDatabaseInternational(); // Load DB data into JTable  
        loadDataFromDatabaseLocal();
    }
    
 private void loadDataFromDatabaseInternational() {
        // NOTE: Replace these with your actual database credentials
        String url = "jdbc:mysql://localhost:3306/hotel_reservation";  
        String user = "root";  
        String password = "password"; 

        // CRITICAL CHECK: Ensure roomTable is initialized before attempting to use it.
        if (roomTableLocal == null) {
            logger.log(Level.SEVERE, "roomTableInternational is null. initComponents() might have failed or not been called.");
            JOptionPane.showMessageDialog(this, "Application setup error: Room table component not found.", "Setup Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Use try-with-resources to ensure connection is closed
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql = "SELECT * FROM roomrates_international";  
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            // Cast the table model to DefaultTableModel to allow row manipulation
            DefaultTableModel model = (DefaultTableModel) roomTableInternational.getModel();
            model.setRowCount(0); // clear existing rows

            // Loop through the results and add rows to the table model
            while (rs.next()) {
                Object[] row = new Object[9];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("destination_type");
                row[2] = rs.getString("room_type");
                row[3] = rs.getString("capacity");
                row[4] = rs.getInt("available_rooms");
                row[5] = rs.getDouble("rate_lean");
                row[6] = rs.getDouble("rate_high");
                row[7] = rs.getDouble("rate_peak");
                row[8] = rs.getDouble("rate_super_peak");

                model.addRow(row);
            }

        } catch (SQLException e) {
            // Show error message to the user
            JOptionPane.showMessageDialog(this,
                    "Error loading data from database:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            // Log the detailed stack trace
            logger.log(Level.SEVERE, "Database loading error", e);
        }
 }
 
 
        private void loadDataFromDatabaseLocal() {
        // NOTE: Uses the same credentials, adjust if necessary
        String url = "jdbc:mysql://localhost:3306/hotel_reservation";  
        String user = "root";  
        String password = "password"; 

        if (roomTableLocal == null) {
            logger.log(Level.SEVERE, "roomTableLocal is null. Cannot load data.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Query for Local Rates
            String sql = "SELECT * FROM roomrates_local";  
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) roomTableLocal.getModel();
            model.setRowCount(0); // clear existing rows

            // Reuse the same column structure for simplicity
            while (rs.next()) {
                Object[] row = new Object[9];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("destination_type");
                row[2] = rs.getString("room_type");
                row[3] = rs.getString("capacity");
                row[4] = rs.getInt("available_rooms");
                row[5] = rs.getDouble("rate_lean");
                row[6] = rs.getDouble("rate_high");
                row[7] = rs.getDouble("rate_peak");
                row[8] = rs.getDouble("rate_super_peak");

                model.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading Local data:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            logger.log(Level.SEVERE, "Database loading error (Local)", e);
        }
    
        
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
        roomTableLocal = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        roomTableInternational = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ROOM VIEW");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        roomTableLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Destination Type", "Room Type", "Capacity", "Available rooms", "Lean", "High", "Peak", "Super Peak"
            }
        ));
        jScrollPane1.setViewportView(roomTableLocal);

        roomTableInternational.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Destination Type", "Room Type", "Capacity", "Available rooms", "Lean", "High", "Peak", "Super Peak"
            }
        ));
        jScrollPane2.setViewportView(roomTableInternational);

        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MAINMENU booking = new MAINMENU();
        booking.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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

       /* Set the Nimbus look and feel */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, "Failed to set look and feel", ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new roomavailability().setVisible(true));
    
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable roomTableInternational;
    private javax.swing.JTable roomTableLocal;
    // End of variables declaration//GEN-END:variables
}
