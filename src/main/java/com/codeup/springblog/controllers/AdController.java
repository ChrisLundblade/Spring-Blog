package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdController {

    private final AdRepository adRepository;

    public AdController(AdRepository adRepository){
        this.adRepository = adRepository;
    }

    @GetMapping("/ads/{id}")
    @ResponseBody
    public String showSingleAd(@PathVariable long id){
        return "Showing ad: " +id;
    }

    @GetMapping("/ads")
    @ResponseBody
    public List<Ad> showAds(){

//        System.out.println(adRepository.findAll());
        return adRepository.findAll();
    }

    @GetMapping("/ads/{title}")
    @ResponseBody
    public Ad getByTitle(@PathVariable String title){
        return adRepository.findByTitle(title);
    }

    @GetMapping("/ads/byTitlePart")
    @ResponseBody
    public List<Ad> getByTitlePart(@RequestParam(name="titlePart") String titlePart){
        return adRepository.findByTitleLike(titlePart);
    }

    @GetMapping("/ads/{description}")
    @ResponseBody
    public Ad getByDescription(@PathVariable String description){
        return adRepository.findByDescription(description);
    }

    @PostMapping("/ads")
    @ResponseBody
    public String createAd(@RequestBody Ad newAd){
        adRepository.save(newAd);
        return String.format("Ad created with an ID of: %s", newAd.getId());}
}
