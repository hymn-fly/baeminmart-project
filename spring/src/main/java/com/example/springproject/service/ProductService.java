package com.example.springproject.service;

import com.example.springproject.domain.Product;
import com.example.springproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product createProduct(String name, String imgUrl, long price){
        return productRepository.save(new Product(name, imgUrl, price));
    }
}
