package com.codeup.blog.springbootblog.controller.controller;

import com.codeup.blog.springbootblog.controller.models.User;
import com.codeup.blog.springbootblog.controller.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private PasswordEncoder encoder;
    private UsersRepository repository;

    public UserController(PasswordEncoder encoder, UsersRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return"users/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        repository.save(user);

        return "redirect:/login";
    }

}
