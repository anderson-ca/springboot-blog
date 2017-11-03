package com.codeup.blog.springbootblog.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostsController {
    @GetMapping("/post")
    @ResponseBody
    public String post() {
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postIndividual(String id) {
        return "Individual post" + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postFormView() {
        return "form for create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postFormCreate() {
        return "create new post";
    }
}
