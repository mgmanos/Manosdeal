package com.deal.Manosdeal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "products_id", unique = false)
    private Products product;
    private int quantity;
    private double price;

}

