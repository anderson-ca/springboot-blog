package com.codeup.blog.springbootblog.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldApplication {
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello, World!!";
    }
    @ResponseBody
    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello, " +  name + " !!";
    }

    @ResponseBody
    @GetMapping("/hello/{firstName}/{lastName}")
    public String helloFullname(@PathVariable String firstName, @PathVariable String lastName) {
        return "<h1>Hello, " + firstName + " " + lastName + " !!</h1>";
    }
}
