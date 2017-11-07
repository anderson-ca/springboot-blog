package com.codeup.blog.springbootblog.controller.models;

public class Post {

    private String title;
    private String body;
    private long id;

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
