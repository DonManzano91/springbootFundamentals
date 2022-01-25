package com.manzano.fundspring.fundamentosSpring.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.manzano.fundspring.fundamentosSpring.Entity.Post;


@Entity
@Table(name = "user")
public class User {

    //Definimos los atributos de la tabla que estamos mapeando
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false, unique = true)
    private long id;

    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String email;
    //@Column(length = 50)
    private LocalDate dateBirth;

    /*Así como mapeamos en Post ManyToOne, debemos mapear de vuelta el OneToMany de esta tabla hacia la de Post*/
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Post> Post = new ArrayList<>();

    public User() {
    }

    public User(String name, String email, LocalDate dateBirth) {
        this.name = name;
        this.email = email;
        this.dateBirth = dateBirth;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public List<Post> getPost() {
        return Post;
    }

    public void setPost(List<Post> post) {
        Post = post;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateBirth=" + dateBirth +
                ", Post=" + Post +
                '}';
    }
}
