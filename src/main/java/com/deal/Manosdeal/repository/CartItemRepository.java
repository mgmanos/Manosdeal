package com.deal.Manosdeal.repository;

import com.deal.Manosdeal.model.Cart;
import com.deal.Manosdeal.model.CartItems;
import com.deal.Manosdeal.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Integer> {
    CartItems findCartItemByProduct(Products product);

    List<CartItems> findByCart(Cart cart);
}
