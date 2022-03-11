package com.manzano.fundspring.fundamentosSpring.Dto;

import java.time.LocalDate;

/**
 * Dto que nos ayudara a representar el usario a nivel JPQL
 *
 * */

public class UserDto {

    private long id;
    private String name;
    private LocalDate dateBirth;

    public UserDto(long id, String name, LocalDate dateBirth) {
        this.id = id;
        this.name = name;
        this.dateBirth = dateBirth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return dateBirth;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.dateBirth = birthDate;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + dateBirth + '\'' +
                '}';
    }
}
