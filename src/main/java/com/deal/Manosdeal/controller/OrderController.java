package com.deal.Manosdeal.controller;

import com.deal.Manosdeal.dto.OrderItemResponse;
import com.deal.Manosdeal.dto.OrderResponse;
import com.deal.Manosdeal.model.User;
import com.deal.Manosdeal.service.OrderService;
import com.deal.Manosdeal.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("/showorderui")
    public String showOrderList(Model model) {
        User user = UserUtil.getCurrentUser();
        List<OrderResponse> orderResponses = service.getAllOrder(user);
        model.addAttribute("orderResponses", orderResponses);
        return "orderlist";
    }

    @GetMapping("/showorderdetailui/{orderid}")
    public String showOrderDetailList(@PathVariable int orderid, Model model) {
        List<OrderItemResponse> orderItemResponses = service.getOrderDetails(orderid);
        model.addAttribute("orderItemResponses", orderItemResponses);
        return "orderdetaillist";
    }

    @PostMapping("/placeorder")
    @ResponseBody  // Ensures JSON response instead of Thymeleaf template
    public ResponseEntity<Map<String, String>> placeOrder() {
        User user = UserUtil.getCurrentUser();
        ResponseEntity<?> response = service.placeOrder(user);

        Map<String, String> responseBody = new HashMap<>();

        if (response.getStatusCode().is2xxSuccessful()) {
            responseBody.put("message", "Order placed successfully!");
            return ResponseEntity.ok(responseBody);  // HTTP 200 (Success) + JSON Response
        } else {
            responseBody.put("message", "Failed to place order");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }
    }

}



