package com.deal.Manosdeal.repository;

import com.deal.Manosdeal.model.OrderItems;
import com.deal.Manosdeal.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Integer> {
    List<OrderItems> findByOrder(Orders order);
}

