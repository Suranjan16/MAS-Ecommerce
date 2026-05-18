package com.suranjan.mas.cart.service;

import com.suranjan.mas.auth.entity.User;
import com.suranjan.mas.cart.dto.AddToCartRequest;
import com.suranjan.mas.cart.entity.Cart;
import com.suranjan.mas.cart.entity.CartItem;
import com.suranjan.mas.cart.repository.CartItemRepository;
import com.suranjan.mas.cart.repository.CartRepository;
import com.suranjan.mas.product.entity.Product;
import com.suranjan.mas.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ProductRepository productRepository)
    {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public String addToCart(User user, AddToCartRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        CartItem cartItem = cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElseGet(() -> {

                    CartItem newItem = new CartItem();

                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    newItem.setQuantity(0);

                    return newItem;
                });

        cartItem.setQuantity(
                cartItem.getQuantity() + request.getQuantity()
        );

        cartItemRepository.save(cartItem);

        return "Product added successfully";
    }
}
