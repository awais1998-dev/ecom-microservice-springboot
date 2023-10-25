package com.tech.productservice.service;

import com.tech.productservice.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product createProduct(Product product);
    public void deleteProduct(Long id);
}
