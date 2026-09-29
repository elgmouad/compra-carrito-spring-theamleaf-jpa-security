package org.example.compracarrito.controller;

import org.example.compracarrito.model.Product;
import org.example.compracarrito.services.CartService;
import org.example.compracarrito.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    public CartController(
            CartService cartService,
            ProductService productService) {

        this.cartService = cartService;
        this.productService = productService;
    }

    // Afficher le panier
    @GetMapping
    public String showCart(Model model) {

        model.addAttribute(
                "cartItems",
                cartService.getCartItems()
        );

        return "cart";
    }

    // Ajouter un produit au panier
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id) {

        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Producto no encontrado: " + id
                ));

        cartService.addToCart(product);

        return "redirect:/cart";
    }

    // Supprimer un produit du panier
    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {

        cartService.removeFromCart(id);

        return "redirect:/cart";
    }
}
