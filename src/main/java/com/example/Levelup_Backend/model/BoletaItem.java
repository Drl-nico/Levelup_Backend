package com.example.Levelup_Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class BoletaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private int amount;
    private int price;
    private int subtotal;

    @ManyToOne
    @JoinColumn(name = "boleta_id")
    @JsonIgnore
    private Boleta boleta;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getSubtotal() { return subtotal; }
    public void setSubtotal(int subtotal) { this.subtotal = subtotal; }

    public Boleta getBoleta() { return boleta; }
    public void setBoleta(Boleta boleta) { this.boleta = boleta; }
}
