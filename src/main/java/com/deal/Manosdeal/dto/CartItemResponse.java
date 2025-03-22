package com.deal.Manosdeal.dto;

import lombok.Data;

@Data
public class CartItemResponse {
    private int cartitemid;
    private int productid;
    private String name;
    private double price;
    private int quantity;
    private double totalprice;
}
