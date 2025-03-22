package com.deal.Manosdeal.controller;

import com.deal.Manosdeal.dto.ProductRequest;
import com.deal.Manosdeal.model.Products;
import com.deal.Manosdeal.model.User;
import com.deal.Manosdeal.service.CartService;
import com.deal.Manosdeal.service.ProductService;
import com.deal.Manosdeal.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private CartService cartService;

    @GetMapping("/productui")
    public String showProduct(Model model) {
        ProductRequest request = new ProductRequest();
        model.addAttribute("product", request);
        return "add-product";
    }

    @PostMapping("/saveproduct")
    public String addProduct(@ModelAttribute ProductRequest productRequest, RedirectAttributes redirectAttributes) {
        ResponseEntity<?> response = service.addProduct(productRequest);

        if (response.getStatusCode().is2xxSuccessful()) {
            redirectAttributes.addFlashAttribute("successMessage", "Product added successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add product");
        }
        return "redirect:/productui";
    }
    @GetMapping("/productlistui")
    public String showProductList(Model model) {
        List<Products> products = service.getAllProduct();
        model.addAttribute("productList", products);
        return "productlist";
    }

    @PostMapping("/addcart")
    public String addToCart(@RequestParam("productid") int productid, RedirectAttributes redirectAttributes) {
        User user = UserUtil.getCurrentUser();
        cartService.addCart(productid, user);
        redirectAttributes.addFlashAttribute("successMessage", "Product successfully added to cart!");
        return "redirect:/productlistui";
    }

}
