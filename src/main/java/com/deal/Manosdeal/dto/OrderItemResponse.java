package com.deal.Manosdeal.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderItemResponse {
    private int orderid;
    private Date orderdate;
    private String productname;
    private int quantity;
    private double totalprice;
}

