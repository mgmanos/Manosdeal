package com.deal.Manosdeal.repository;

import com.deal.Manosdeal.model.Cart;
import com.deal.Manosdeal.model.CartItems;
import com.deal.Manosdeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartByUser(User user);

    List<CartItems> findByUser(User user);

    void deleteByUser(User user);
}
