package com.example.assignment2.service;

import com.example.assignment2.model.Category;
import com.example.assignment2.model.Product;
import com.example.assignment2.repository.CategoryRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;
    EntityManager entityManager;

    public CategoryService(CategoryRepository categoryRepository, EntityManager entityManager){
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    public Category add(Category category){
        System.out.println(category);
        Category categoryResult = categoryRepository.save(category);
        return categoryResult;
    }

    public List<Category> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public Category getCategoryById(int id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent())
            return category.get();
        return null;
    }

    public Category updateCategory(int id, Category category) {

        if(categoryRepository.existsById(id)){

            Category updateCategory = getCategoryById(id);

            if(category.getDescription()!=null)
                updateCategory.setDescription( category.getDescription());
            if(category.getName()!=null)
                updateCategory.setName( category.getName());

            return add(updateCategory);
        }
        return null;
    }

//    public Category updateCategory(int it, Category category){
//        if(categoryRepository.existsById(category.getId())){
//            category.setId(it);
//            return entityManager.merge(category);
//        }
//        return null;
//    }

    public boolean deleteById(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isEmpty()){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Category> getCategoryByAnyField(Category category){
        if(category.getDescription()==null)
            category.setDescription("%");
        if(category.getName()==null)
            category.setName("%");

        return categoryRepository.getCategoryByAnyField(category.getName(), category.getDescription());
    }

}
