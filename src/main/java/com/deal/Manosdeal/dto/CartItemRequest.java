package com.deal.Manosdeal.dto;

import lombok.Data;

@Data
public class CartItemRequest {
    private int productId;
    private int quantity;
}
