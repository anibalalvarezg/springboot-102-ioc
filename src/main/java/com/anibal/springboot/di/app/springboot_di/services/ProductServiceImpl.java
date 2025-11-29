package com.anibal.springboot.di.app.springboot_di.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anibal.springboot.di.app.springboot_di.models.Product;
import com.anibal.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
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

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }
}
 