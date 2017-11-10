package com.codeup.blog.springbootblog.controller.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    ///////////////////////////////////////////////////////////////////
    // Fields
    ///////////////////////////////////////////////////////////////////
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

    public User() {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // This line is SUPER important! Many things won't work if it's absent (Copy constructor)
    // used to create a new object using another object's property values. This will be used in
    // order to fulfill the contract defined by the interfaces in the security package.
    ////////////////////////////////////////////////////////////////////////////////////////////
    public User(User copy) {
        id = copy.id;
        name = copy.name;
        email = copy.email;
        password = copy.password;
    }

    ///////////////////////////////////////////////////////////////////
    // Setters and Getters
    ///////////////////////////////////////////////////////////////////
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
