package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostImage;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    //injects a dependency
    private final PostRepository postRepository; //aka postsDao

    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    public String index(Model model){
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
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
    public String create(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String insert(@RequestParam String title, @RequestParam String body, @RequestParam List<String> urls){
        List<PostImage> images = new ArrayList<>();
        Post post = new Post(title, body);
        for(String url : urls){
            PostImage postImage= new PostImage(url);
            postImage.setPost(post);
            images.add(postImage);
        }

        post.setImages(images);

        postRepository.save(post);
        return "redirect:/posts";
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

    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String delete(@PathVariable long id){
        postRepository.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String returnEditView(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("post", postRepository.getById(id));
        return "posts/edit";
    }

    @PostMapping("posts/{id}/edit")
    public String edit(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Post post = postRepository.getById(id);
        post.setTitle(title);
        post.setBody(body);
        postRepository.save(post);

        return "redirect:/posts";
    }


}
