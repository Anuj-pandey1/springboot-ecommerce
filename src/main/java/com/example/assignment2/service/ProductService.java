package com.example.assignment2.service;

import com.example.assignment2.model.Category;
import com.example.assignment2.model.Product;
import com.example.assignment2.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;
    EntityManager entityManager;

    public ProductService(ProductRepository productRepository, EntityManager entityManager){
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }

    public Product add(Product product){
        Product productResult = productRepository.save(product);
        return productResult;
    }

    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product getProductById(int id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent())
            return product.get();
        return null;
    }

    public Product updateProduct(int id, Product product) {

        if(productRepository.existsById(id)){

            Product updateProduct = getProductById(id);

            if(product.getCategory()!=null)
                updateProduct.setCategory( product.getCategory() );
            if(product.getPrice()!=0)
                updateProduct.setPrice( product.getPrice() );
            if(product.getName()!=null)
                updateProduct.setName( product.getName());
            if(product.getDescription()!=null)
                updateProduct.setDescription( product.getDescription());

            return add(updateProduct);
        }
        return null;
    }

//    public Product updateProduct(int id, Product product) throws IllegalAccessException {
//
//
//
//        if(productRepository.existsById(id)){
//
//            Product updateProduct = getProductById(id);
//
//
//            // Iterate over all declared fields
//            for (Field field : product.getClass().getDeclaredFields()) {
//                // Make the private fields accessible
//                field.setAccessible(true);
//
//                // Get the field name
//                String fieldName = field.getName();
//
//                // Get the field value
//                Object fieldValue = field.get(product);
//
//                // Print the field name and value
//                System.out.println(fieldName + ": " + fieldValue);
//                if(fieldValue!=null)
//                field.set(updateProduct, fieldValue);
//            }
//            return add(product);
//        }
//        return null;
//    }

    public boolean deleteById(Integer id){
        Optional<Product> product = productRepository.findById(id);
        if(!product.isEmpty()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> getProductsByAnyField(Product product) {

        if(product.getName()==null)
            product.setName("%");

        if(product.getDescription()==null)
            product.setDescription("%");

        if(product.getPrice()==null) {
            return productRepository.getProductByAnyField(
                    product.getName(),
                    product.getDescription());
        }

        return productRepository.getProductByAnyField(
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }

}
