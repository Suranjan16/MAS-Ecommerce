package com.suranjan.mas.service;

import com.suranjan.mas.entity.Product;
import com.suranjan.mas.exception.ProductNotFoundException;
import com.suranjan.mas.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product updateProduct(Long id,Product product) {
        Product existingProduct = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

            existingProduct.setName(product.getName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());

            return repository.save(existingProduct);

    }

    public void deleteProduct(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        repository.delete(product);
    }

    public List<Product> getProductByCategory(String category) {
        return repository.findByCategory(category);
    }

    public List<Product> getProductByName(String name) {
        return repository.findByNameContaining(name);
    }

    public Page<Product> getProductsWithPaginationAndSorting(int page, int size, String field, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(field).ascending() :
                Sort.by(field).descending();

        return repository.findAll(PageRequest.of(page, size, sort));
    }
}