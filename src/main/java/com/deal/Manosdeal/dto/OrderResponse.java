package com.deal.Manosdeal.dto;

import lombok.Data;

import java.util.Date;

@Data

public class OrderResponse {
    private int orderid;
    private Date orderdate;
    private double totalprice;
}
