package com.deal.Manosdeal.controller;

import com.deal.Manosdeal.dto.OrderItemResponse;
import com.deal.Manosdeal.dto.OrderResponse;
import com.deal.Manosdeal.model.User;
import com.deal.Manosdeal.service.OrderService;
import com.deal.Manosdeal.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderRestController {
    @Autowired
    private OrderService service;

    @GetMapping("/getallorder")
    public List<OrderResponse> getAllOrder() {
        User user = UserUtil.getCurrentUser();
        return service.getAllOrder(user);
    }

    @GetMapping("/orderdetail/{orderId}")
    public List<OrderItemResponse> getOrderDetails(@PathVariable int orderId) {
        return service.getOrderDetails(orderId);
    }
}
