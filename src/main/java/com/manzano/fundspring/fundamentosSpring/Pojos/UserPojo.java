package com.manzano.fundspring.fundamentosSpring.Pojos;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/*Clase/Pojo que al usar la segunda anotación permite ser usada como dependencia configurable a traves de parametros
* definidos en el archivo de configuraciones application.properties*/
@ConstructorBinding
@ConfigurationProperties(prefix = "user")
public class UserPojo {

    private String Mail;
    private String Password;
    private int age;

    public UserPojo(String mail, String password, int age) {
        Mail = mail;
        Password = password;
        this.age = age;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
