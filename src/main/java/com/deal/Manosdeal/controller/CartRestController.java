package com.deal.Manosdeal.controller;

import com.deal.Manosdeal.dto.CartItemResponse;
import com.deal.Manosdeal.model.User;
import com.deal.Manosdeal.service.CartService;
import com.deal.Manosdeal.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartRestController {
    @Autowired
    private CartService service;

    @GetMapping("/cartlist")
    public List<CartItemResponse> getCarItem() {
        User user = UserUtil.getCurrentUser();
        return service.getCartItems(user);
    }


}
