package com.example.assignment2.repository;

import com.example.assignment2.model.Category;
import com.example.assignment2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "Select p from Product p where p.name like :name and p.description like :description")
    List<Product> getProductByAnyField(String name, String description);

    @Query(value = "Select p from Product p where p.name like :name and p.description like :description and price = :price")
    List<Product> getProductByAnyField(String name, String description, Integer price);

}
