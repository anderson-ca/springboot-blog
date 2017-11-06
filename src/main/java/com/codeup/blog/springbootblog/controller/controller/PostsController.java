package com.codeup.blog.springbootblog.controller.controller;

import com.codeup.blog.springbootblog.controller.models.Post;
import com.codeup.blog.springbootblog.controller.services.PostSvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class PostsController {

    private PostSvc postSvc;

    @Autowired
    public PostsController(PostSvc postSvc) {
        this.postSvc = postSvc;
    }

    @GetMapping("/posts")
    public String showAll(Model vModel) {

        vModel.addAttribute("posts", postSvc.findAll());

        return "posts/index";

    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable int id, Model vModel) {

        Post post = new Post("Example 1", "this is another example");

        vModel.addAttribute("post", post);

        return "posts/show";

    }

    @GetMapping("/posts/create")
    public String showCreateForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    public String createPost() {
        return "create a new post";
    }

}
