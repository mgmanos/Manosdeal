package com.deal.Manosdeal.controller;

import com.deal.Manosdeal.model.Products;
import com.deal.Manosdeal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    private ProductService service;

    @GetMapping("/getproducts")
    public List<Products> getAllProduct() {
        return service.getAllProduct();
    }
}
