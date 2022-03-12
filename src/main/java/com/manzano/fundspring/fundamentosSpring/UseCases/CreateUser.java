package com.manzano.fundspring.fundamentosSpring.UseCases;

import com.manzano.fundspring.fundamentosSpring.Entity.User;
import com.manzano.fundspring.fundamentosSpring.Service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {

    UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }


    public User saveNewUser(User newUser) {
        return userService.saveNewUser(newUser);
    }
}
