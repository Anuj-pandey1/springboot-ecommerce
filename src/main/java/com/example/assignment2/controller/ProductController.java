package com.example.assignment2.controller;

import com.example.assignment2.model.Category;
import com.example.assignment2.model.Product;
import com.example.assignment2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public Product save(@RequestBody Product product){
        return productService.add(product);
    }

    @PostMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        System.out.println(id);
        System.out.println(product);
        return productService.updateProduct(id,product);
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public List<Product> getProductsByAnyField(@RequestBody Product product){
        return productService.getProductsByAnyField(product);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        return productService.deleteById(id);
    }






}
