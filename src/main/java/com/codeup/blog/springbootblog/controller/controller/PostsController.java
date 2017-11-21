package com.codeup.blog.springbootblog.controller.controller;

import com.codeup.blog.springbootblog.controller.models.Post;
import com.codeup.blog.springbootblog.controller.models.User;
import com.codeup.blog.springbootblog.controller.repositories.PostsRepository;
import com.codeup.blog.springbootblog.controller.repositories.UsersRepository;
import com.codeup.blog.springbootblog.controller.services.PostSvc;
import com.codeup.blog.springbootblog.controller.services.UserSvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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

    //////////////////////////////////////////////////////////////////////////////////
    // Constructor method for the post controller. Receiving Dependency injection from
    // various models(classes).
    //////////////////////////////////////////////////////////////////////////////////
    @Autowired
    public PostsController(PostsRepository postsDao, PostSvc postsSvc, UsersRepository usersDao, UserSvc usersSvc) {
        this.postsDao = postsDao;
        this.postsSvc = postsSvc;
        this.usersDao = usersDao;
        this.usersSvc = usersSvc;
    }

    //////////////////////////////////////////////////////////////////////////////////
    // display all posts in post table. -> Constructor Injection.
    //////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/posts")
    public String showAll(Model vModel) {

        vModel.addAttribute("posts", postsDao.findAll());

        return "posts/index";

    }

    //////////////////////////////////////////////////////////////////////////////////
    // Show individual post when clicked by the user.
    //////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable int id, Model vModel) {

        vModel.addAttribute("post", postsDao.findOne((long) id));

        return "posts/show";

    }

    //////////////////////////////////////////////////////////////////////////////////
    // Display form used in order to create new post.
    //////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Create post and save the information input in the create form to the post table
    //////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/posts/create")
    public String createPost(@Valid Post post,
                             Errors validation,
                             Model model,
                             RedirectAttributes redirect
    ) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }

        if (!usersSvc.isLoggedIn()) {
            redirect.addFlashAttribute("test", true);
            return "redirect:/register";
        }
        post.setUser(usersSvc.loggedInUser());
        postsDao.save(post);

        return "redirect:/posts";
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Display form used in order to edit existing post.
    //////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/posts/edit/{id}")
    public String showEditForm(@PathVariable int id, Model vModel) {
        vModel.addAttribute("post", postsDao.findOne((long) id));
        return "posts/edit";
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Edit post and save the information input in the edit form to the post table
    //////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/posts/edit/{id}")
    public String editPost(@Valid Post post,
                           Errors validation,
                           Model model, @PathVariable int id, RedirectAttributes redirect

    ) {

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/edit";
        }

        if (!usersSvc.isLoggedIn()) {
            redirect.addFlashAttribute("test", true);
            return "redirect:/register";
        }

        post.setUser(usersSvc.loggedInUser());
        postsDao.save(post);
        return "redirect:/posts/profile";
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Method used to display posts in JSON Format. Obs: the response body annotation
    // tells Spring to skip the default behavior
    //////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/posts.json")
    public @ResponseBody
    Iterable<Post> viewAllPostsInJsonFormat() {
        return postsDao.findAll();
    }

    //////////////////////////////////////////////////////////////////////////////////
    // View all posts through a AJAX call using jQuery from the view.
    //////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/posts/ajax")
    public String viewAllPostsWithAjax() {
        return "posts/ajax";
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Display all posts the ID matches with logged in user ID
    //////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/posts/profile")
    public String showLoggedUserPosts(Model vModel) {
        if (!usersSvc.isLoggedIn()) {
            return "redirect:/register";

        }
        User user = usersSvc.loggedInUser();
        List<Post> posts = postsDao.findByUserId(user.getId());

        vModel.addAttribute("posts", posts);

        vModel.addAttribute("user", user);

        return "posts/profile";

    }

    //////////////////////////////////////////////////////////////////////////////////
    // Display all posts the ID matches with logged in user ID
    //////////////////////////////////////////////////////////////////////////////////
    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable int id) {

        postsDao.delete( (long) id);

        return "redirect:/posts/profile";
    }

}
