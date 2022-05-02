package com.example.springproject.controller;

import com.example.springproject.service.ProductService;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(Model model){
        val products = productService.getProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/new-product")
    public String newProduct(){
        return "new-product";
    }

    @PostMapping("/new-product")
    public String createNewProduct(ProductCreateRequest productCreateRequest){
        productService.createProduct(productCreateRequest.name(), productCreateRequest.url(), productCreateRequest.price());

        return "redirect:/products";
    }

}
