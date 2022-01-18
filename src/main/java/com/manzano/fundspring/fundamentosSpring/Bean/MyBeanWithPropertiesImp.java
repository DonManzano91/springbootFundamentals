package com.manzano.fundspring.fundamentosSpring.Bean;

public class MyBeanWithPropertiesImp implements MyBeanWithProperties{

    private String nombre;
    private String apellido;

    public MyBeanWithPropertiesImp(String name, String apellido) {
        this.nombre = name;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return nombre + " + " + apellido;
    }
}
