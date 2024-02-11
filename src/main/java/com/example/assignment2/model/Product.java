package com.example.assignment2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "price")
    private Integer price;
    @Column(name = "product_description")
    private String description;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id")
    private Category category;

}
