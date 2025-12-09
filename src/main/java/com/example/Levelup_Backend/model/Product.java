package com.example.Levelup_Backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;
    private double price;
    private String img;

    // nuevo campo stock
    private int stock = 0;

    // Constructores
    public Product() {}

    public Product(String title, String category, double price, String img, int stock) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.img = img;
        this.stock = stock;
    }

    // Getters y Setters (incluyendo stock)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}