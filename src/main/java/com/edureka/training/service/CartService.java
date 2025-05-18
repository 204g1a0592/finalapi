package com.edureka.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edureka.training.entity.Cart;
import com.edureka.training.entity.CartItem;
import com.edureka.training.entity.Product;
import com.edureka.training.entity.UserCredential;
import com.edureka.training.repository.CartItemRepository;
import com.edureka.training.repository.CartRepository;
import com.edureka.training.repository.ProductRepository;
import com.edureka.training.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

     UserRepository userRepository;
    ProductRepository productRepository;
    CartRepository cartRepository;
    CartItemRepository cartItemRepository;
   
    public CartService(UserRepository userRepository, ProductRepository productRepository,
                       CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional
    public void addToCart(Long userId, Long productId, int quantity) {
        UserCredential user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        System.out.println("founf product"+product.getProductname());

        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElse(null);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCart(cart);
         //   cartItem.setProduct(product.getProductname());
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            //cartItem.setProduct(product);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItemRepository.save(cartItem);

        // Reduce stock in product
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
    public List<CartItem> getCartItems(Long userId) {
        return cartItemRepository.findByUserIdWithProduct(userId);
    }


//    public CartService(UserRepository userRepository, ProductRepository productRepository,
//                       CartRepository cartRepository, CartItemRepository cartItemRepository) {
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//        this.cartRepository = cartRepository;
//        this.cartItemRepository = cartItemRepository;
//    }
//
//    public void addToCart(Long userId, Long productId, int quantity) {
//        Optional<UserCredential> users = userRepository.findById(userId);
//        		//.orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        Product product = productRepository.findById(productId)
//            .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        if (product.getQuantity() < quantity) {
//            throw new IllegalArgumentException("Not enough stock available");
//        }
//        UserCredential user=users.get();
//        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
//            Cart newCart = new Cart();
//            newCart.setUser(user);
//            return cartRepository.save(newCart);
//        });
//
//        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);
//
//        if (existingItem.isPresent()) {
//            CartItem item = existingItem.get();
//            int newQuantity = item.getQuantity() + quantity;
//            if (newQuantity > product.getQuantity()) {
//                throw new IllegalArgumentException("Not enough stock available");
//            }
//            item.setQuantity(newQuantity);
//            cartItemRepository.save(item);
//        } else {
//            CartItem newItem = new CartItem();
//            newItem.setCart(cart);
//            newItem.setProduct(product);
//            newItem.setQuantity(quantity);
//            cartItemRepository.save(newItem);
//        }
//    }

    // Add other methods here: updateQuantity, removeFromCart, getCartByUsername etc.
    
    
    @Transactional
    public void removeFromCart(Long userId, Long cartItemId) {
        UserCredential user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("CartItem doesn't belong to user's cart");
        }

        // Restore stock
        Product product = item.getProduct();
        product.setQuantity(product.getQuantity() + item.getQuantity());
        productRepository.save(product);

        // Remove item
        cartItemRepository.delete(item);
    }

}
