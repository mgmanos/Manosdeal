package com.deal.Manosdeal.repository;

import com.deal.Manosdeal.model.Orders;
import com.deal.Manosdeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByUser(User user);
}
