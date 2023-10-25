package com.tech.productservice.serviceImpl;

import com.tech.productservice.model.Product;
import com.tech.productservice.repository.ProductRepository;
import com.tech.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found with id : " + id)
        );
        productRepository.delete(product);
    }
}
