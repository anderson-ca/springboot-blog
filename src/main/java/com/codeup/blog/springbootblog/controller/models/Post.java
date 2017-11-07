package com.codeup.blog.springbootblog.controller.models;
import javax.persistence.*;

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
    private String title;

    //////////////////////////////////////////////////////////////////////////////////
    // Sets column value and if its nullable.
    // This mapping is equivalent to creating a MySQL table definition.
    //////////////////////////////////////////////////////////////////////////////////
    @Column(nullable = false)
    private String body;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post() {

    }

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

    public long getId() { return this.id; }

    public void setId(int id) { this.id = id; }

}
