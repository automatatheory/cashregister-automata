/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cashregister;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hanna
 */
public class RecordsFrame extends javax.swing.JFrame {
    
    private JTable transactionsTable;
    private JTable itemsTable;
    private DefaultTableModel transactionsModel;
    private DefaultTableModel itemsModel;

    /**
     * Creates new form RecordsFrame
     */
    public RecordsFrame() {
        setTitle("Records");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#0d0d1a"));
        getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("RECORDS");
        titleLabel.setFont(new java.awt.Font("Roboto Mono", java.awt.Font.BOLD, 20));
        titleLabel.setForeground(new java.awt.Color(253, 119, 215));;
        titleLabel.setBounds(370, 20, 200, 40);
        getContentPane().add(titleLabel);
        
        JLabel leftLabel = new JLabel("TRANSACTIONS");
        leftLabel.setFont(new Font("Roboto Mono", Font.BOLD, 14));
        leftLabel.setForeground(new java.awt.Color(253, 119, 215));;
        leftLabel.setBounds(20, 70, 200, 30);
        getContentPane().add(leftLabel);

        transactionsModel = new DefaultTableModel(
            new String[]{"ID", "DATE", "PAYMENT", "TOTAL"}, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        transactionsTable = new JTable(transactionsModel);
        transactionsTable.setBackground(Color.decode("#1a1a2e"));
        transactionsTable.setForeground(Color.WHITE);
        transactionsTable.setFont(new java.awt.Font("Roboto Mono", 0, 12));
        transactionsTable.getTableHeader().setBackground(Color.decode("#1a1a2e"));
        transactionsTable.getTableHeader().setForeground(new java.awt.Color(253, 119, 215));;
        transactionsTable.setSelectionBackground(Color.decode("#3a3a5e"));
        transactionsTable.setRowHeight(25);

        JScrollPane leftScroll = new JScrollPane(transactionsTable);
        leftScroll.setBounds(20, 110, 400, 420);
        leftScroll.getViewport().setBackground(Color.decode("#1a1a2e"));
        getContentPane().add(leftScroll);

        JLabel rightLabel = new JLabel("ITEMS");
        rightLabel.setFont(new Font("Roboto Mono", Font.BOLD, 14));
        rightLabel.setForeground(new java.awt.Color(253, 119, 215));;
        rightLabel.setBounds(450, 70, 200, 30);
        getContentPane().add(rightLabel);

        itemsModel = new DefaultTableModel(
            new String[]{"PRODUCT", "QTY", "SUBTOTAL"}, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        itemsTable = new JTable(itemsModel);
        itemsTable.setBackground(Color.decode("#1a1a2e"));
        itemsTable.setForeground(Color.WHITE);
        itemsTable.setFont(new java.awt.Font("Roboto Mono", 0, 12));
        itemsTable.getTableHeader().setBackground(Color.decode("#1a1a2e"));
        itemsTable.getTableHeader().setForeground(new java.awt.Color(253, 119, 215));;
        itemsTable.setSelectionBackground(Color.decode("#3a3a5e"));
        itemsTable.setRowHeight(25);

        JScrollPane rightScroll = new JScrollPane(itemsTable);
        rightScroll.setBounds(450, 110, 420, 420);
        rightScroll.getViewport().setBackground(Color.decode("#1a1a2e"));
        getContentPane().add(rightScroll);

        loadTransactions();

        // load items after clicking a transaction
        transactionsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = transactionsTable.getSelectedRow();
                if (selectedRow != -1) {
                    int transactionId = (int) transactionsModel.getValueAt(selectedRow, 0);
                    loadItems(transactionId);
                }
            }
        });
    }
    
    private void loadTransactions() {
        String sql = "SELECT transaction_id, transaction_date, payment_method, total FROM transactions ORDER BY transaction_date ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            transactionsModel.setRowCount(0);
            while (rs.next()) {
                transactionsModel.addRow(new Object[]{
                    rs.getInt("transaction_id"),
                    rs.getTimestamp("transaction_date"),
                    rs.getString("payment_method").toUpperCase(),
                    String.format("₱%.2f", rs.getDouble("total"))
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void loadItems(int transactionId) {
        String sql = "SELECT p.product_name, ti.quantity, ti.subtotal " +
                     "FROM transaction_items ti " +
                     "JOIN products p ON ti.product_id = p.product_id " +
                     "WHERE ti.transaction_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, transactionId);
            ResultSet rs = stmt.executeQuery();
            
            itemsModel.setRowCount(0);
            while (rs.next()) {
                itemsModel.addRow(new Object[]{
                    rs.getString("product_name"),
                    rs.getInt("quantity"),
                    String.format("₱%.2f", rs.getDouble("subtotal"))
                });
            }
            
            System.out.println("Showing transaction history for ID: " + transactionId);
        } catch (SQLException e) {
            e.printStackTrace();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RecordsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecordsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecordsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecordsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecordsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
