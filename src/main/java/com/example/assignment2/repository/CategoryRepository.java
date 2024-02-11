package com.example.assignment2.repository;

import com.example.assignment2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "Select c from Category c where c.name like :name and c.description like :description")
    List<Category> getCategoryByAnyField(String name, String description);

}
