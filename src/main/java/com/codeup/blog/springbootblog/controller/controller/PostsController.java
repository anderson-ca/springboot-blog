package com.codeup.blog.springbootblog.controller.controller;

import com.codeup.blog.springbootblog.controller.models.Post;
import com.codeup.blog.springbootblog.controller.services.PostSvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.sun.tools.doclets.formats.html.markup.HtmlStyle;

@Controller
public class PostsController {

    private final PostSvc postSvc;

    @Autowired
    public PostsController(PostSvc postSvc) {
        this.postSvc = postSvc;
    }

    // Constructor Injection.
    @GetMapping("/posts")
    public String showAll(Model vModel) {

        vModel.addAttribute("posts", postSvc.findAll());

        return "posts/index";

    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable int id, Model vModel) {

        vModel.addAttribute("post", postSvc.findOnePost(id));

        return "posts/show";

    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        postSvc.save(post);

        return"redirect:/posts";

    }

}
