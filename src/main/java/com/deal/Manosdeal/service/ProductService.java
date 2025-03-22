package com.deal.Manosdeal.service;

import com.deal.Manosdeal.dto.ProductRequest;
import com.deal.Manosdeal.model.Products;
import com.deal.Manosdeal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;


    public ResponseEntity<?> addProduct(ProductRequest productrequest) {
        Products product = new Products();
        product.setName(productrequest.getName());
        product.setPrice(productrequest.getPrice());
        product.setCategory(productrequest.getCategory());
        product.setDescription(productrequest.getDescription());
        product.setCreatedDate(LocalDateTime.now());
        product.setModifiedDate(LocalDateTime.now());
        repository.save(product);
        return ResponseEntity.ok("Product added successfully!");
    }

    public List<Products> getAllProduct() {
        return repository.findAll();
    }

    public Products getProduct(int id) {
        return repository.findById(id).orElse(new Products());
    }
}
