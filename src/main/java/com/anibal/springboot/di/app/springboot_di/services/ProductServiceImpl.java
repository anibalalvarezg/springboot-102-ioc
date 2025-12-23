package com.anibal.springboot.di.app.springboot_di.services;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.anibal.springboot.di.app.springboot_di.models.Product;
import com.anibal.springboot.di.app.springboot_di.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private Environment environment;
    private ProductRepository productRepository;
    
     public ProductServiceImpl(ProductRepository productRepository, Environment environment) {
        this.productRepository = productRepository;
        this.environment = environment;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream()
            .map(p -> {
                Double newPrice = p.getPrice() * environment.getProperty("config.price.tax", Double.class);
                // Product newProduct = new Product();
                // newProduct.setId(p.getId());
                // newProduct.setName(p.getName());
                // newProduct.setPrice(newPrice.longValue());
          
 
                p.setPrice(newPrice.longValue());
                return p;
            })
            .toList();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }
}
 