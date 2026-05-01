package com.suranjan.mas.product.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;
    @NotBlank(message = "Category is required")
    private String category;
    @Positive(message = "Price must be always greater than 0")
    private double price;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    public Product() {
    }

}