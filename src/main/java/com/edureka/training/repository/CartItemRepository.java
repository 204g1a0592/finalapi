package com.edureka.training.repository;

import java.util.List;
import java.util.Optional;

import com.edureka.training.entity.Cart;
import com.edureka.training.entity.CartItem;
import com.edureka.training.entity.Product;
import com.edureka.training.entity.UserCredential;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

	List<CartItem> findAllById(Long userId);
	@Query("SELECT ci FROM CartItem ci JOIN FETCH ci.product WHERE ci.cart.user.id = :userId")
	List<CartItem> findByUserIdWithProduct(@Param("userId") Long userId);

	Optional<CartItem> findByUserAndProduct(UserCredential user, Product product);
	Optional<CartItem> findByUserCredentialAndProduct(UserCredential userCredential, Product product);


}
