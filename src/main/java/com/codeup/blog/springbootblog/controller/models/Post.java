package com.codeup.blog.springbootblog.controller.models;

import com.codeup.blog.springbootblog.controller.repositories.UsersRepository;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "posts")
public class Post {
    //////////////////////////////////////////////////////////////////////////////////
    // Sets the ID as the primary key.
    //////////////////////////////////////////////////////////////////////////////////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //////////////////////////////////////////////////////////////////////////////////
    // Sets column value and if its nullable.
    //////////////////////////////////////////////////////////////////////////////////
    @Column(nullable = false, length = 100)
    @NotBlank(message = "Posts must have a title.") //// Validation
    @Size(min = 3, message = "A title must be at least three characters.") //// Validation
    private String title;

    //////////////////////////////////////////////////////////////////////////////////
    // Sets column value and if its nullable.
    // This mapping is equivalent to creating a MySQL table definition.
    //////////////////////////////////////////////////////////////////////////////////
    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Posts must have a body.") //// Validation
    private String body;

    //////////////////////////////////////////////////////////////////////////////////
    // Forms a bond to the user table retrieves the id related to an User instance
    // in order to establish a relation between that user and this post.
    //////////////////////////////////////////////////////////////////////////////////
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    //////////////////////////////////////////////////////////////////////////////////
    // Sets the ID as the primary key.
    //////////////////////////////////////////////////////////////////////////////////



    public Post() {

    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, Long id, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Setter and Getters
    //////////////////////////////////////////////////////////////////////////////////

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }


}
