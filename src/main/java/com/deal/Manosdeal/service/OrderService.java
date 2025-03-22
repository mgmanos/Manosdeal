package com.deal.Manosdeal.service;

import com.deal.Manosdeal.dto.OrderItemResponse;
import com.deal.Manosdeal.dto.OrderResponse;
import com.deal.Manosdeal.model.*;
import com.deal.Manosdeal.repository.*;
import com.deal.Manosdeal.util.UserUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public ResponseEntity<?> placeOrder(User user) {
        Cart cart = cartRepository.findCartByUser(user);
        List<CartItems> cartItems = cart.getCartItems();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }


        Orders order = new Orders();
        order.setUser(user);
        order.setDate(new Date());
        order.setTotalPrice(calculateTotalPrice(cartItems));

        Orders savedOrder = repository.save(order);

        List<OrderItems> orderItems = new ArrayList<>();

        for (CartItems cartItem : cartItems) {
            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            orderItems.add(orderItem);
        }
        orderItemRepository.saveAll(orderItems);
        cartRepository.deleteByUser(user);

        return ResponseEntity.ok("Order placed successfully!");
    }


    private double calculateTotalPrice(List<CartItems> cartItems) {
        return cartItems.stream()
                .mapToDouble(c -> c.getProduct().getPrice() * c.getQuantity())
                .sum();
    }

    public List<OrderResponse> getAllOrder(User user) {
        List<Orders> orders = new ArrayList<>();
        if (UserUtil.isAdmin(user)) {
            orders = repository.findAll();

        }
        else
        orders = repository.findByUser(user);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Orders order : orders) {
            OrderResponse res = new OrderResponse();
            res.setOrderid(order.getId());
            res.setOrderdate(order.getDate());
            res.setTotalprice(order.getTotalPrice());
            orderResponses.add(res);
        }
        return orderResponses;
    }

    public List<OrderItemResponse> getOrderDetails(int orderId) {
        Orders order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        List<OrderItemResponse> response = new ArrayList<>();
        List<OrderItems> items = orderItemRepository.findByOrder(order);
        for (OrderItems item : items) {
            OrderItemResponse res = new OrderItemResponse();
            res.setOrderid(item.getOrder().getId());
            res.setOrderdate(item.getOrder().getDate());
            res.setProductname(item.getProduct().getName());
            res.setQuantity(item.getQuantity());
            res.setTotalprice(item.getQuantity() * item.getProduct().getPrice());
            response.add(res);
        }
        return response;
    }
}
