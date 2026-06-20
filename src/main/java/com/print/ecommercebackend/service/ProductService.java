package com.print.ecommercebackend.service;

import com.print.ecommercebackend.dto.CreateProductRequest;
import com.print.ecommercebackend.entity.Product;
import com.print.ecommercebackend.exception.ProductNotFoundException;
import com.print.ecommercebackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
       .orElseThrow(() ->
            new ProductNotFoundException("Product not found"));
    }

    public Product updateProduct(Long id, CreateProductRequest request) {

        Product product = productRepository.findById(id)
       .orElseThrow(() ->
            new ProductNotFoundException("Product not found"));

        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}