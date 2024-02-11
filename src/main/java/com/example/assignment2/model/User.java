package com.example.assignment2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "password1")
    private String password1;

    @Column(name = "password2")
    private String password2;

    @Column(name = "email")
    private String email;

}
