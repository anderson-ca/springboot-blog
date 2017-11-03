package com.codeup.blog.springbootblog.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{x}/and/{y}")
    @ResponseBody
    public Integer add(@PathVariable Integer x, @PathVariable Integer y) {
        return (x + y);
    }

    @GetMapping("/subtract/{x}/from/{y}")
    @ResponseBody
    public Integer subtract(@PathVariable Integer x, @PathVariable Integer y) {
        return (y - x);
    }

    @GetMapping("/multiple/{x}/by/{y}")
    @ResponseBody
    public Integer multiply(@PathVariable Integer x, @PathVariable Integer y) {
        return (x * y);
    }

    @GetMapping("/divide/{x}/by/{y}")
    @ResponseBody
    public Double divide(@PathVariable double x, @PathVariable double y) {
        return (x / y);
    }

}
