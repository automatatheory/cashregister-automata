/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cashregister;

/**
 *
 * @author Dana
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity = 1;
    

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        String tableStart = "<html><table width='190'>"; 
        String deleteIcon = "<font color='red'>X</font>";
    
        if (this.quantity == -1) {
            return tableStart +
               "<tr>" +
               "<td align='left'>" + name + "</td>" +
               "<td align='right'>\u20B1" + String.format("%.2f", price) + "</td>" +
               "</tr></table></html>";
        } else {
            String quantityDisplay = "<font color='#FD77D7'>[x" + quantity + "]</font>";
            return tableStart +
                "<tr>" +
                "<td align='left'>" + name + "</td>" +
                "<td align='right'>" + quantityDisplay + "&nbsp;&nbsp; " + "\u20B1" + String.format("%.2f", price * quantity) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + deleteIcon + "</td>" +
                "</tr></table></html>";
        }
    }


    public static List<Product> getInitialInventory() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product(
                    rs.getInt("product_id"), 
                    rs.getString("product_name"), 
                    rs.getDouble("unit_price")
                );
                p.setQuantity(-1); 
                
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
            /*
            List<Product> items = new ArrayList<>();
            items.add(new Product("Soda", 29.50));
            items.add(new Product("Chips", 34.25));
            items.add(new Product("Burger", 50.00));
            items.add(new Product("Coffee", 99.75));
            items.add(new Product("Donut", 30.25));
            return items;*/
        }
}

