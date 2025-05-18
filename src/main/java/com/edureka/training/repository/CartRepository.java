package com.edureka.training.repository;

import java.util.Optional;

import com.edureka.training.entity.Cart;
import com.edureka.training.entity.Product;
import com.edureka.training.entity.UserCredential;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(UserCredential user);
}

