package com.deal.Manosdeal.repository;

import com.deal.Manosdeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<Object> findByUsername(String username);
}
