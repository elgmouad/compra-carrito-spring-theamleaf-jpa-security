package org.example.compracarrito.services;

import org.example.compracarrito.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> getAllProducts();

    public Optional<Product> getProductById(Long id);

    public Product saveProduct(Product product) ;

    public void deleteProduct(Long id);

}
