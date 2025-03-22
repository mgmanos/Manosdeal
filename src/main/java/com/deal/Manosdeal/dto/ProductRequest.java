package com.deal.Manosdeal.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private int id;
    private String name;
    private double price;
    private String category;
    private String description;
}
