package com.example.assignment2.controller;

import com.example.assignment2.model.Category;
import com.example.assignment2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add-category")
    public Category save(@RequestBody Category category){
        return categoryService.add(category);
    }

    @GetMapping("/")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/{id}")
    public Category update(@PathVariable int id, @RequestBody Category category){
        return categoryService.updateCategory(id,category);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        return categoryService.deleteById(id);
    }

    @PostMapping("/")
    public List<Category> getCategoryByAnyField(@RequestBody Category category){
        return categoryService.getCategoryByAnyField(category);
    }


}
