package com.manzano.fundspring.fundamentosSpring.Component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependency{

    @Override
    public void saludar() {
        System.out.println("Hola, esto es springboot");
    }
}
