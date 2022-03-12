package com.manzano.fundspring.fundamentosSpring.UseCases;

import com.manzano.fundspring.fundamentosSpring.Entity.User;
import com.manzano.fundspring.fundamentosSpring.Service.UserService;

import java.util.List;

public class GetUserImpl implements GetUser{

    UserService userService;

    public GetUserImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }
}
