package com.manzano.fundspring.fundamentosSpring.UseCases;

import com.manzano.fundspring.fundamentosSpring.Service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {

    UserService userService;

    public DeleteUser(UserService userService) {
        this.userService = userService;
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }
}
