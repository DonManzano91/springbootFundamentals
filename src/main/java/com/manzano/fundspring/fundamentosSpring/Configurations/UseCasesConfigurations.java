package com.manzano.fundspring.fundamentosSpring.Configurations;

import com.manzano.fundspring.fundamentosSpring.Service.UserService;
import com.manzano.fundspring.fundamentosSpring.UseCases.GetUser;
import com.manzano.fundspring.fundamentosSpring.UseCases.GetUserImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfigurations {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImpl(userService);
    }
}
