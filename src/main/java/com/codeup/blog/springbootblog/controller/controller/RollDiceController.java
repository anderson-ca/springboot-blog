package com.codeup.blog.springbootblog.controller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@Controller
public class RollDiceController {

    @GetMapping("/rolldice")
    public String defaultPage() {
        return "rolldice";
    }

    @GetMapping("/rolldice/{test}")
    public String randNum(@PathVariable int test, Model viewModel) {

        List<Integer> listGuess = new ArrayList<>();

        listGuess.add(1);
        listGuess.add(2);
        listGuess.add(3);
        listGuess.add(4);
        listGuess.add(5);
        listGuess.add(6);

        int randNum = (int) (Math.random() * 6 + 1);

        String bingo = "";

        if (randNum == test) {
            bingo = "Bingo!";
        }

        viewModel.addAttribute("listGuess", listGuess);

        viewModel.addAttribute("bingo", bingo);

        viewModel.addAttribute("test", test);

        viewModel.addAttribute("rand", randNum);

        // Return value.
        return "rolldice";
    }

}