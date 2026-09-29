package org.example.compracarrito.services;

import org.example.compracarrito.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final List<Product> cart = new ArrayList<>();

    public List<Product> getCartItems() {
        return cart;
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void removeFromCart(Long productId) {
        cart.removeIf(product -> product.getId().equals(productId));
    }

    public void clearCart() {
        cart.clear();
    }

}
