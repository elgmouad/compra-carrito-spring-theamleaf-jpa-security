package org.example.compracarrito.controller;

import org.example.compracarrito.model.Product;
import org.example.compracarrito.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Afficher tous les produits
    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "productos";
    }

    // Afficher le formulaire pour ajouter un produit
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "formulario-producto";
    }

    // Ajouter un produit
    @PostMapping
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Afficher le formulaire de modification
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Producto no encontrado: " + id
                ));

        model.addAttribute("product", product);

        return "formulario-producto";
    }

    // Supprimer un produit
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return "redirect:/products";
    }
}
