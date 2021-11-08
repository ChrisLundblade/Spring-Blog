package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostImage;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
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
    private final EmailService emailService;
    public final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository, EmailService emailService){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.emailService=emailService;
    }

    @GetMapping("/posts")
    public String index(Model model){
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }



//    @GetMapping("/posts/{title}")
//    @ResponseBody
//    public Post getByTitle(String title){
//        return postRepository.findByTitle(title);
//    }

    @GetMapping("/posts/{id}")
    public String showOnePost(@PathVariable long id, Model model){
        model.addAttribute("post", postRepository.getById(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String create(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String insert(@ModelAttribute Post post, @RequestParam List<String> urls){
        List<PostImage> images = new ArrayList<>();
        for(String url : urls){
            PostImage postImage= new PostImage(url);
            postImage.setPost(post);
            images.add(postImage);
        }

        post.setImages(images);
        post.setUser(userRepository.getById(1L)); //just using the first user for now

        postRepository.save(post);
        emailService.prepareAndSend(post, "You made " + post.getTitle() + ".", post.getBody());
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
    public String edit(@PathVariable long id, @ModelAttribute("post") Post post, @RequestParam  List<String> urls){
        Post originalPost = postRepository.getById(id);
        originalPost.setBody(post.getBody());
        originalPost.setTitle(post.getTitle());
        //post.setId(id);
        List<PostImage> images = new ArrayList<>();
        for(String url : urls){
            PostImage postImage= new PostImage(url);
            postImage.setPost(post);
            images.add(postImage);
        }
        post.setImages(images);
        for(PostImage image : post.getImages()){
            System.out.println(image.getUrl());
        }
        //post.setUser(userRepository.getById(1L));
        postRepository.save(originalPost);
        return "redirect:/posts";
    }


}
