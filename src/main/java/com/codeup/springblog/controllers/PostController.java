package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String getPosts(){
        return "Here are all of the posts (but there are none right now).";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPost(@PathVariable long id){
        return "Here is the post with the id of " + id +":";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getPostCreationForm(){
        return "Here is the form to create a post: ";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postCreatedPost(){
        return "This is the post, I guess.";
    }
}
