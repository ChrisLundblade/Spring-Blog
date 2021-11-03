package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DieRollController {

    @GetMapping("/roll-dice/{guess}")
    public String dieRollGuesser(@PathVariable int guess, Model model){
        int rollResult = (int) Math.floor((Math.random()*6) +1);
        if(guess == rollResult){
            model.addAttribute("guessMessage", "Your guess was correct");
        } else{
            model.addAttribute("guessMessage", "Your guess was incorrect. Try again");
        }
        model.addAttribute("rollResult", rollResult);
        return "/views-exercise/dieRollGuess";
    }
}
