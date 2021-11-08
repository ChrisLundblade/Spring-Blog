package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.AdRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdController {

    private final AdRepository adRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;

    public AdController(AdRepository adRepository, EmailService emailService, UserRepository userRepository){
        this.adRepository = adRepository;
        this.emailService= emailService;
        this.userRepository = userRepository;
    }

    @GetMapping("/ads/{id}")
    @ResponseBody
    public String showSingleAd(@PathVariable long id){
        return "Showing ad: " +id;
    }

    @GetMapping("/ads")
    public String showAds(Model model){

//        System.out.println(adRepository.findAll());
        model.addAttribute("ads", adRepository.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/create")
    public String showCreateAdsForm(Model model){
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAdWithForm(@ModelAttribute Ad ad){
        User user = userRepository.getById(1L);
        ad.setOwner(user);
        adRepository.save(ad);
        emailService.prepareAndSend(ad, "You made " + ad.getTitle() + ".", ad.getDescription());
        return "redirect:/ads";
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
