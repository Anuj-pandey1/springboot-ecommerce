package com.example.assignment2.service;

import com.example.assignment2.model.User;
import com.example.assignment2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private TokenService tokenService;

    public UserService(UserRepository userRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public User save(User user){
        // do proper exception handling
        if(existByUserName(user.getUserName()))
            return null;

        return userRepository.save(user);
//        return user;
    }

    public String login(String userName, String password){          // add wrong username, password exception also
        Optional<User> user = Optional.ofNullable(userRepository.getLogin(userName, password));
        System.out.println(user.get());
        if(user.get().getPassword1().equals(password)) {
            return "{" +
                    "\"message\":"+"Successfully logged in\",\n"+
                    "\"data\":"+user.get()+",\n"+
                    "\"Email\":"+user.get().getEmail()+"\n"+
                    "\"token\":"+tokenService.createTokenMethod(user.get().getId())+"\""+
                    "}";
        }

        return "{" +
                "\"message\":"+"Authentication Failed\",\n"+
                "}";

    }

    public boolean existByUserName(String userName) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserName(userName));
        return user.isPresent();
    }


}
