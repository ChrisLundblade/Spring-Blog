package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PostController {

    //injects a dependency
    private final PostRepository postRepository; //aka postsDao

    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<Post> index(){
        return postRepository.findAll();
    }



    @GetMapping("/posts/{title}")
    @ResponseBody
    public Post getByTitle(String title){
        return postRepository.findByTitle(title);
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable long id){
        return "Here is the post with the id of " + id +":";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String create(Post newPost){
        postRepository.save(newPost);
        return String.format("Post created with an ID of: %s", newPost.getId());
    }

//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String insert(){
//        return "This is the post, I guess.";
//    }

//    @GetMapping("/posts/delete")
//    @ResponseBody
//    public String delete(){
//        return RedirectView;
//    }

    @PostMapping("/posts/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable long id){
        postRepository.deleteById(id);
        return "The ad with the id of "+id+" was deleted.";
    }

    @PostMapping("posts/edit/{updatedPost}")
    @ResponseBody
    public Post edit(@PathVariable Post updatedPost){
        postRepository.editByID(updatedPost.getId(), updatedPost.getTitle(), updatedPost.getBody());
        return postRepository.getById(updatedPost.getId());
    }


}
