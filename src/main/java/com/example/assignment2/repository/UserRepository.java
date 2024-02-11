package com.example.assignment2.repository;

import ch.qos.logback.core.model.INamedModel;
import com.example.assignment2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "Select u from User u where userName = ?1 and password1 = ?2")
    User getLogin(String userName, String password);

    @Query(value = "Select u from User u where userName = ?1")
    User findByUserName(String userName);
}
