package com.codeup.blog.springbootblog.controller.controller;

import com.codeup.blog.springbootblog.controller.models.Post;
import com.codeup.blog.springbootblog.controller.repositories.PostsRepository;
import com.codeup.blog.springbootblog.controller.repositories.UsersRepository;
import com.codeup.blog.springbootblog.controller.services.PostSvc;
import com.codeup.blog.springbootblog.controller.services.UserSvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

//////////////////////////////////////////////////////////////////////////////////
// No hard logic should be accomplished in my controller. The only thing I should
// do is control the flow of information from the view to the model.
//////////////////////////////////////////////////////////////////////////////////
@Controller
public class PostsController {

    private final PostsRepository postsDao;

    private final UsersRepository usersDao;

    private final UserSvc usersSvc;

    private final PostSvc postsSvc;


    @Autowired
    public PostsController(PostsRepository postsDao, PostSvc postsSvc, UsersRepository usersDao, UserSvc usersSvc) {
        this.postsDao = postsDao;
        this.postsSvc = postsSvc;
        this.usersDao = usersDao;
        this.usersSvc = usersSvc;
    }

    // Constructor Injection.
    @GetMapping("/posts")
    public String showAll(Model vModel) {

        vModel.addAttribute("posts", postsDao.findAll());

        return "posts/index";

    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable int id, Model vModel) {

        vModel.addAttribute("post", postsDao.findOne((long) id));

        return "posts/show";

    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@Valid Post post,
                             Errors validation,
                             Model model
    ) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }
        post.setUser(usersSvc.loggedInUser());
        postsDao.save(post);

        return "redirect:/posts";
    }

}
