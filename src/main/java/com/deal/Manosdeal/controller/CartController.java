package com.deal.Manosdeal.controller;


import com.deal.Manosdeal.dto.CartItemResponse;
import com.deal.Manosdeal.model.User;
import com.deal.Manosdeal.service.CartService;
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

public class CartController {
    @Autowired
    private CartService service;
    @GetMapping("/showcartui")
    public String showCart(Model model) {
        User user = UserUtil.getCurrentUser();
        List<CartItemResponse> cartItemResponses =  service.getCartItems(user);
        model.addAttribute("cartItemResponses", cartItemResponses);
        return "cartitemlist";
    }
    @DeleteMapping("/removecartitem/{cartitemid}")
    public ResponseEntity<Map<String, String>> removeFromCart(@PathVariable int cartitemid) {
        ResponseEntity<?> response = service.removeFromCart(cartitemid);

        Map<String, String> responseBody = new HashMap<>();
        if (response.getStatusCode().is2xxSuccessful()) {
            responseBody.put("message", "Cart item removed successfully");
            return ResponseEntity.ok(responseBody);
        } else {
            responseBody.put("message", "Failed to remove cart item");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
    }


}
