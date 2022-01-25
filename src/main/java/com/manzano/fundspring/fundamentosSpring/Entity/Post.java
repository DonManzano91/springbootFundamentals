package com.manzano.fundspring.fundamentosSpring.Entity;

import javax.persistence.*;
import com.manzano.fundspring.fundamentosSpring.Entity.User;

@Entity
@Table(name = "post")
public class Post {

    //Definimos los atributos de la tabla que estamos mapeando
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false, unique = true)
    private long id;

    @Column(name = "description", length = 255)
    private String description;

    //Definiremos la relación que otras tablas tengan con esta:
    @ManyToOne
    private User User;

    public Post() {
    }

    public Post(String description, User user) {
        this.description = description;
        User = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", User=" + User +
                '}';
    }
}
