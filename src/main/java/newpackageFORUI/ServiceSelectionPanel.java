
package newpackageFORUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Cursor;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ServiceSelectionPanel extends javax.swing.JFrame {
    
    //Decleration
    private int poolGuests;
    private int poolSeniorPWD;

    private int spaGuests;
    private int spaSeniorPWD;

    private int gymGuests;
    private int gymSeniorPWD;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ServiceSelectionPanel.class.getName());

    public ServiceSelectionPanel() {
        initComponents();
        poolPanel.setVisible(false);
        spaPanel.setVisible(false);
        gymPanel.setVisible(false);
        setSize(800, 550);
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        poolPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        poolSeniorPWDTextArea = new javax.swing.JTextArea();
        poolGuestsTextArea = new javax.swing.JScrollPane();
        poolGuestTextArea = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        poolGuestsInput = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextArea12 = new javax.swing.JTextArea();
        jScrollPane16 = new javax.swing.JScrollPane();
        poolSeniorPWDInput = new javax.swing.JTextArea();
        poolPanel1 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        poolSeniorPWDTextArea1 = new javax.swing.JTextArea();
        poolGuestsTextArea1 = new javax.swing.JScrollPane();
        poolGuestTextArea1 = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        poolGuestsInput1 = new javax.swing.JTextArea();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextArea13 = new javax.swing.JTextArea();
        jScrollPane20 = new javax.swing.JScrollPane();
        poolSeniorPWDInput1 = new javax.swing.JTextArea();
        spaToggleButton = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        poolToggleButton1 = new javax.swing.JToggleButton();
        gymPanel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        gymGuestsTextArea = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        gymGuestsInput = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        gymSeniorPWDTextArea = new javax.swing.JTextArea();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextArea15 = new javax.swing.JTextArea();
        jScrollPane17 = new javax.swing.JScrollPane();
        gymSeniorPWDInput = new javax.swing.JTextArea();
        gymToggleButton = new javax.swing.JToggleButton();
        spaPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        spaGuestsTextArea = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        spaGuestsInput = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        spaSeniorPWDTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        spaSeniorPWDInput = new javax.swing.JTextArea();
        backButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        poolPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        poolPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        poolSeniorPWDTextArea.setEditable(false);
        poolSeniorPWDTextArea.setColumns(20);
        poolSeniorPWDTextArea.setRows(5);
        poolSeniorPWDTextArea.setText("No. Senior Citizen/PWD");
        poolSeniorPWDTextArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        poolSeniorPWDTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        poolSeniorPWDTextArea.setFocusable(false);
        jScrollPane5.setViewportView(poolSeniorPWDTextArea);

        poolPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 140, 30));

        poolGuestsTextArea.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        poolGuestsTextArea.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        poolGuestTextArea.setEditable(false);
        poolGuestTextArea.setColumns(20);
        poolGuestTextArea.setRows(5);
        poolGuestTextArea.setText("No. of Guests");
        poolGuestTextArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        poolGuestTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        poolGuestTextArea.setFocusable(false);
        poolGuestsTextArea.setViewportView(poolGuestTextArea);

        poolPanel.add(poolGuestsTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 30));

        jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane10.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        poolGuestsInput.setColumns(20);
        poolGuestsInput.setRows(5);
        poolGuestsInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        poolGuestsInput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane10.setViewportView(poolGuestsInput);

        poolPanel.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 60, 30));

        jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane12.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea12.setEditable(false);
        jTextArea12.setColumns(20);
        jTextArea12.setRows(5);
        jTextArea12.setText("No. Senior Citizen/PWD");
        jTextArea12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextArea12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextArea12.setFocusable(false);
        jScrollPane12.setViewportView(jTextArea12);

        poolPanel.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 140, 30));

        jScrollPane16.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane16.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        poolSeniorPWDInput.setColumns(20);
        poolSeniorPWDInput.setRows(5);
        poolSeniorPWDInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        poolSeniorPWDInput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane16.setViewportView(poolSeniorPWDInput);

        poolPanel.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 60, 30));

        poolPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        poolPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        poolSeniorPWDTextArea1.setEditable(false);
        poolSeniorPWDTextArea1.setColumns(20);
        poolSeniorPWDTextArea1.setRows(5);
        poolSeniorPWDTextArea1.setText("No. Senior Citizen/PWD");
        poolSeniorPWDTextArea1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        poolSeniorPWDTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        poolSeniorPWDTextArea1.setFocusable(false);
        jScrollPane8.setViewportView(poolSeniorPWDTextArea1);

        poolPanel1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 140, 30));

        poolGuestsTextArea1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        poolGuestsTextArea1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        poolGuestTextArea1.setEditable(false);
        poolGuestTextArea1.setColumns(20);
        poolGuestTextArea1.setRows(5);
        poolGuestTextArea1.setText("No. of Guests");
        poolGuestTextArea1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        poolGuestTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        poolGuestTextArea1.setFocusable(false);
        poolGuestsTextArea1.setViewportView(poolGuestTextArea1);

        poolPanel1.add(poolGuestsTextArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 30));

        jScrollPane18.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane18.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        poolGuestsInput1.setColumns(20);
        poolGuestsInput1.setRows(5);
        poolGuestsInput1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        poolGuestsInput1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane18.setViewportView(poolGuestsInput1);

        poolPanel1.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 60, 30));

        jScrollPane19.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane19.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea13.setEditable(false);
        jTextArea13.setColumns(20);
        jTextArea13.setRows(5);
        jTextArea13.setText("No. Senior Citizen/PWD");
        jTextArea13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextArea13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextArea13.setFocusable(false);
        jScrollPane19.setViewportView(jTextArea13);

        poolPanel1.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 140, 30));

        jScrollPane20.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane20.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        poolSeniorPWDInput1.setColumns(20);
        poolSeniorPWDInput1.setRows(5);
        poolSeniorPWDInput1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        poolSeniorPWDInput1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane20.setViewportView(poolSeniorPWDInput1);

        poolPanel1.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 60, 30));

        poolPanel.add(poolPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 460, 50));

        getContentPane().add(poolPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 760, 50));

        spaToggleButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        spaToggleButton.setText("Spa");
        spaToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spaToggleButtonActionPerformed(evt);
            }
        });
        getContentPane().add(spaToggleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setText("Lanlya Hotel offers the following services. Please enter the number of guest(s) for each selected service, as well as the number of \nsenior citizens/PWDs. Leave empty if you don't want to avail any services.");
        jTextArea3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextArea3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextArea3.setFocusable(false);
        jScrollPane3.setViewportView(jTextArea3);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 760, 50));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SELECT SERVICES");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 760, 50));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea4.setEditable(false);
        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setText("Lanlya Hotel offers the following services. Please enter the number of guest(s) for \neach selected service, as well as the number of senior citizens/PWDs.");
        jTextArea4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextArea4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextArea4.setFocusable(false);
        jScrollPane4.setViewportView(jTextArea4);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 460, 50));

        poolToggleButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        poolToggleButton1.setText("Pool");
        poolToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poolToggleButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(poolToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        gymPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gymPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        gymGuestsTextArea.setEditable(false);
        gymGuestsTextArea.setColumns(20);
        gymGuestsTextArea.setRows(5);
        gymGuestsTextArea.setText("No. of Guests");
        gymGuestsTextArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gymGuestsTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gymGuestsTextArea.setFocusable(false);
        jScrollPane9.setViewportView(gymGuestsTextArea);

        gymPanel.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 30));

        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        gymGuestsInput.setColumns(20);
        gymGuestsInput.setRows(5);
        gymGuestsInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gymGuestsInput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane11.setViewportView(gymGuestsInput);

        gymPanel.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 60, 30));

        jScrollPane14.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane14.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        gymSeniorPWDTextArea.setEditable(false);
        gymSeniorPWDTextArea.setColumns(20);
        gymSeniorPWDTextArea.setRows(5);
        gymSeniorPWDTextArea.setText("No. Senior Citizen/PWD");
        gymSeniorPWDTextArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gymSeniorPWDTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gymSeniorPWDTextArea.setFocusable(false);
        jScrollPane14.setViewportView(gymSeniorPWDTextArea);

        gymPanel.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 140, 30));

        jScrollPane15.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane15.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea15.setEditable(false);
        jTextArea15.setColumns(20);
        jTextArea15.setRows(5);
        jTextArea15.setText("No. Senior Citizen/PWD");
        jTextArea15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextArea15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextArea15.setFocusable(false);
        jScrollPane15.setViewportView(jTextArea15);

        gymPanel.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 140, 30));

        jScrollPane17.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane17.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        gymSeniorPWDInput.setColumns(20);
        gymSeniorPWDInput.setRows(5);
        gymSeniorPWDInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        gymSeniorPWDInput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane17.setViewportView(gymSeniorPWDInput);

        gymPanel.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 60, 30));

        getContentPane().add(gymPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 760, 50));

        gymToggleButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        gymToggleButton.setText("Gym");
        gymToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gymToggleButtonActionPerformed(evt);
            }
        });
        getContentPane().add(gymToggleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        spaPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        spaPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        spaGuestsTextArea.setEditable(false);
        spaGuestsTextArea.setColumns(20);
        spaGuestsTextArea.setRows(5);
        spaGuestsTextArea.setText("No. of Guests");
        spaGuestsTextArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        spaGuestsTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spaGuestsTextArea.setFocusable(false);
        jScrollPane6.setViewportView(spaGuestsTextArea);

        spaPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 30));

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        spaGuestsInput.setColumns(20);
        spaGuestsInput.setRows(5);
        spaGuestsInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        spaGuestsInput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane7.setViewportView(spaGuestsInput);

        spaPanel.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 60, 30));

        jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane13.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        spaSeniorPWDTextArea.setEditable(false);
        spaSeniorPWDTextArea.setColumns(20);
        spaSeniorPWDTextArea.setRows(5);
        spaSeniorPWDTextArea.setText("No. Senior Citizen/PWD");
        spaSeniorPWDTextArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        spaSeniorPWDTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spaSeniorPWDTextArea.setFocusable(false);
        jScrollPane13.setViewportView(spaSeniorPWDTextArea);

        spaPanel.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 140, 30));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        spaSeniorPWDInput.setColumns(20);
        spaSeniorPWDInput.setRows(5);
        spaSeniorPWDInput.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        spaSeniorPWDInput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(spaSeniorPWDInput);

        spaPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 60, 30));

        getContentPane().add(spaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 760, 50));

        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 420, 110, 40));

        confirmButton.setText("CONFIRM");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        getContentPane().add(confirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 420, 110, 40));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(null);
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 760, 80));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 760, 80));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(null);
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 760, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void spaToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spaToggleButtonActionPerformed
        spaPanel.setVisible(spaToggleButton.isSelected());
    }//GEN-LAST:event_spaToggleButtonActionPerformed

    private void poolToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poolToggleButton1ActionPerformed
        poolPanel.setVisible(poolToggleButton1.isSelected());
    }//GEN-LAST:event_poolToggleButton1ActionPerformed

    private void gymToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gymToggleButtonActionPerformed
        gymPanel.setVisible(gymToggleButton.isSelected());
    }//GEN-LAST:event_gymToggleButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        if (!validateInputs()) return; // catch non-numeric inputs
    
    int result = JOptionPane.showConfirmDialog(this, 
        "Confirm? Are all these information correct?", 
        "Confirmation", 
        JOptionPane.YES_NO_OPTION);
    
    if (result == JOptionPane.YES_OPTION) {
        // proceed to PaymentPanel
        PaymentPanelMainMenu payment = new PaymentPanelMainMenu();
        payment.setVisible(true);
        this.dispose(); // close current window
    }
    // if NO, do nothing and return
    System.out.println(poolGuests);
    System.out.println(poolSeniorPWD);
    System.out.println(spaGuests);
    System.out.println(spaSeniorPWD);
    System.out.println(gymGuests);
    System.out.println(gymSeniorPWD);

    }//GEN-LAST:event_confirmButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
         int result = JOptionPane.showConfirmDialog(this,
        "Confirm? Are you sure you want to go back to Addon Selection?",
        "Back Confirmation",
        JOptionPane.YES_NO_OPTION);

    if (result == JOptionPane.YES_OPTION) {
        // clear all fields
        poolGuestsInput.setText("");
        poolSeniorPWDInput.setText("");
        spaGuestsInput.setText("");
        spaSeniorPWDInput.setText("");
        gymGuestsInput.setText("");
        gymSeniorPWDInput.setText("");

        AddonSelectionPanel addon = new AddonSelectionPanel();
        addon.setVisible(true);
        this.dispose();
    }
    // if NO, do nothing
    }//GEN-LAST:event_backButtonActionPerformed

   private boolean validateInputs() {
    try {
        // Pool
        if (poolPanel.isVisible()) {
            poolGuests = Integer.parseInt(poolGuestsInput.getText());
            poolSeniorPWD = Integer.parseInt(poolSeniorPWDInput.getText());
        }

        // Spa
        if (spaPanel.isVisible()) {
            spaGuests = Integer.parseInt(spaGuestsInput.getText());
            spaSeniorPWD = Integer.parseInt(spaSeniorPWDInput.getText());
        }

        // Gym
        if (gymPanel.isVisible()) {
            gymGuests = Integer.parseInt(gymGuestsInput.getText());
            gymSeniorPWD = Integer.parseInt(gymSeniorPWDInput.getText());
        }

        return true;

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error. Incorrect Characters");
        return false;
    }
    
   
    
}
 
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
        java.awt.EventQueue.invokeLater(() -> new ServiceSelectionPanel().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JTextArea gymGuestsInput;
    private javax.swing.JTextArea gymGuestsTextArea;
    private javax.swing.JPanel gymPanel;
    private javax.swing.JTextArea gymSeniorPWDInput;
    private javax.swing.JTextArea gymSeniorPWDTextArea;
    private javax.swing.JToggleButton gymToggleButton;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea12;
    private javax.swing.JTextArea jTextArea13;
    private javax.swing.JTextArea jTextArea15;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea poolGuestTextArea;
    private javax.swing.JTextArea poolGuestTextArea1;
    private javax.swing.JTextArea poolGuestsInput;
    private javax.swing.JTextArea poolGuestsInput1;
    private javax.swing.JScrollPane poolGuestsTextArea;
    private javax.swing.JScrollPane poolGuestsTextArea1;
    private javax.swing.JPanel poolPanel;
    private javax.swing.JPanel poolPanel1;
    private javax.swing.JTextArea poolSeniorPWDInput;
    private javax.swing.JTextArea poolSeniorPWDInput1;
    private javax.swing.JTextArea poolSeniorPWDTextArea;
    private javax.swing.JTextArea poolSeniorPWDTextArea1;
    private javax.swing.JToggleButton poolToggleButton1;
    private javax.swing.JTextArea spaGuestsInput;
    private javax.swing.JTextArea spaGuestsTextArea;
    private javax.swing.JPanel spaPanel;
    private javax.swing.JTextArea spaSeniorPWDInput;
    private javax.swing.JTextArea spaSeniorPWDTextArea;
    private javax.swing.JToggleButton spaToggleButton;
    // End of variables declaration//GEN-END:variables
}
