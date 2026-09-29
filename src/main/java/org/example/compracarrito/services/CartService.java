package org.example.compracarrito.services;

import org.example.compracarrito.model.Product;

import java.util.List;


public interface CartService {

    public List<Product> getCartItems() ;

    public void addToCart(Product product) ;

    public void removeFromCart(Long productId) ;

    public void clearCart();

}
