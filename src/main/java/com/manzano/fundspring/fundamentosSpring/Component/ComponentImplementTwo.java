package com.manzano.fundspring.fundamentosSpring.Component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplementTwo implements ComponentDependency{
    @Override
    public void saludar() {
        System.out.println("Este es el segundo Hola mundo");
    }
}
