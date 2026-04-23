package com.suranjan.mas.controller;

import com.suranjan.mas.entity.Product;
import com.suranjan.mas.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return service.getProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product) {
        return service.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        service.deleteProduct(id);
        return "Product Deleted Successfully";
    }
}