package com.anibal.springboot.di.app.springboot_di.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.Resource;

import com.anibal.springboot.di.app.springboot_di.models.Product;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

public class ProductRepositoryJson implements ProductRepository {

    private List<Product> productsList;

    public ProductRepositoryJson(Resource resource) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productsList = Arrays.asList(objectMapper.readValue(resource.getInputStream(), Product[].class));
        } catch (JacksonException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return productsList;
    }

    @Override
    public Product findById(Long id) {
        return productsList.stream()
            .filter(item -> item.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    
}
