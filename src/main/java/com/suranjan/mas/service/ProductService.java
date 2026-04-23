package com.suranjan.mas.service;

import com.suranjan.mas.entity.Product;
import com.suranjan.mas.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProduct(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id,Product product) {
        Product existingProduct = repository.findById(id).orElse(null);

        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());

            return repository.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}