package com.deal.Manosdeal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItems> orderItems = new ArrayList<>();
    private Date date;
    private double totalPrice;

    public void removeOrderItem(OrderItems orderItem) {
        orderItems.remove(orderItem);
        orderItem.setOrder(null);
    }
}
