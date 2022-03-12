package com.manzano.fundspring.fundamentosSpring.UseCases;

import com.manzano.fundspring.fundamentosSpring.Entity.User;
import com.manzano.fundspring.fundamentosSpring.Service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {

    UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = userService;
    }


    public User updateUser(User toUpdateUser, Long id) {
        return userService.updateUser(toUpdateUser, id);
    }
}
