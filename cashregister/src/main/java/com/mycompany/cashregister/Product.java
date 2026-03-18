/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cashregister;

/**
 *
 * @author Dana
 */
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() { 
        return price; 
    }

    @Override
    public String toString() {
        return String.format("%-15s \u20B1%.2f", name, price);
    }


    public static List<Product> getInitialInventory() {
        List<Product> items = new ArrayList<>();
        items.add(new Product("Soda", 29.50));
        items.add(new Product("Chips", 34.25));
        items.add(new Product("Burger", 50.00));
        items.add(new Product("Coffee", 99.75));
        items.add(new Product("Donut", 30.25));
        return items;
    }
}

