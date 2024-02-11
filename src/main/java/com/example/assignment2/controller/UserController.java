package com.example.assignment2.controller;

import com.example.assignment2.model.User;
import com.example.assignment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public User register(@RequestBody User user){
        System.out.println(user);
//        User userResult = userService.save(user);
            return userService.save(user);
//        return userService.login(userResult.getUserName(), userResult.getPassword1());
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.login(user.getUserName(), user.getPassword1());
    }

}
