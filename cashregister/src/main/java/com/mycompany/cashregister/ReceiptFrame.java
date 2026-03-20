/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.cashregister;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author hanna
 */
public class ReceiptFrame extends javax.swing.JFrame {

    /**
     * Creates new form ReceiptFrame
     */
    public ReceiptFrame(DefaultListModel<Product> selectedModel, double subTotal, double vatRate, String paymentMethod, double amountPaid, double change) {
        initComponents();
        setTitle("Receipt");
        setSize(400,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JLabel titleLabel = new JLabel("RECEIPT");
        titleLabel.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 24));
        titleLabel.setBounds(150, 20, 200, 40);
        getContentPane().add(titleLabel);
        getContentPane().setBackground(Color.WHITE);
        
        JTextPane receiptPane = new JTextPane();
        receiptPane.setEditable(false);
        receiptPane.setBackground(Color.WHITE);
        receiptPane.setBounds(20, 80, 360, 400);

        //bold txt for titles
        StyledDocument doc = receiptPane.getStyledDocument();
        Style boldStyle = receiptPane.addStyle("Bold", null);
        StyleConstants.setBold(boldStyle, true);
        StyleConstants.setFontFamily(boldStyle, "Monospaced");
        StyleConstants.setFontSize(boldStyle, 14);
        StyleConstants.setForeground(boldStyle, Color.BLACK);

        //normal txt for products
        Style normalStyle = receiptPane.addStyle("Normal", null);
        StyleConstants.setBold(normalStyle, false);
        StyleConstants.setFontFamily(normalStyle, "Monospaced");
        StyleConstants.setFontSize(normalStyle, 14);
        StyleConstants.setForeground(normalStyle, Color.BLACK);

        try {
            doc.insertString(doc.getLength(), "===========================================\n", boldStyle);
            doc.insertString(doc.getLength(), String.format("%-20s %18s\n", "ITEM", "PRICE"), boldStyle);
            doc.insertString(doc.getLength(), "===========================================\n", boldStyle);

            for (int i = 0; i < selectedModel.size(); i++) {
                Product p = selectedModel.getElementAt(i);
                doc.insertString(doc.getLength(), String.format("%-20s %18s\n",
                    p.getName() + " x" + p.getQuantity(),
                    "₱" + String.format("%.2f", p.getPrice() * p.getQuantity())), normalStyle);
            }

            doc.insertString(doc.getLength(), "-------------------------------------------\n", boldStyle);
            doc.insertString(doc.getLength(), String.format("%-20s %18s\n", 
                "SUBTOTAL:", "₱" + String.format("%.2f", subTotal)), boldStyle);
            doc.insertString(doc.getLength(), String.format("%-20s %18s\n", 
                "VAT", String.format("%.2f%%", vatRate)), boldStyle);
            doc.insertString(doc.getLength(), "===========================================\n", boldStyle);
            double totalWithVat = subTotal * (1 + vatRate);
            doc.insertString(doc.getLength(), String.format("%-20s %18s\n", 
                "TOTAL:", "₱" + String.format("%.2f", totalWithVat)), boldStyle);
            doc.insertString(doc.getLength(), "\n", boldStyle);
            
            doc.insertString(doc.getLength(), String.format("%-20s %18s\n", 
                "PAYMENT METHOD:", paymentMethod), boldStyle);
            
            if (paymentMethod.equals("CASH")) {
                doc.insertString(doc.getLength(), String.format("%-20s %18s\n",
                    "AMOUNT PAID:", "₱" + String.format("%.2f", amountPaid)), normalStyle);
                doc.insertString(doc.getLength(), String.format("%-20s %18s\n",
                    "CHANGE:", "₱" + String.format("%.2f", change)), boldStyle);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        getContentPane().add(receiptPane);
        
        JButton confirmBtn = new JButton("CONFIRM");
        confirmBtn.setBackground(Color.decode("#1a1a2e"));
        confirmBtn.setForeground(Color.PINK);
        confirmBtn.setFont(new Font("Roboto Mono", Font.BOLD, 14));
        confirmBtn.setBounds(150, 510, 100, 40);
        confirmBtn.addActionListener(e -> {
            dispose();
        });
        getContentPane().add(confirmBtn);
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
        setPreferredSize(new java.awt.Dimension(400, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(ReceiptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReceiptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReceiptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReceiptFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReceiptFrame(new javax.swing.DefaultListModel<>(), 0.0, 0.0, "CASH", 0.0, 0.0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
