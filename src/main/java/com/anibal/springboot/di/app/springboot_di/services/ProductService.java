package com.anibal.springboot.di.app.springboot_di.services;

import java.util.List;

import com.anibal.springboot.di.app.springboot_di.models.Product;
import com.anibal.springboot.di.app.springboot_di.repositories.ProductRepository;

public class ProductService {

    private ProductRepository productRepository = new ProductRepository();

    public List<Product> findAll() {
        return productRepository.findAll().stream()
            .map(p -> {
                Double newPrice = p.getPrice() * 1.19d;
                // Product newProduct = new Product();
                // newProduct.setId(p.getId());
                // newProduct.setName(p.getName());
                // newProduct.setPrice(newPrice.longValue());
                Product newProduct = (Product)p.clone();
                newProduct.setPrice(newPrice.longValue());
                return newProduct;
            })
            .toList();
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }
}
