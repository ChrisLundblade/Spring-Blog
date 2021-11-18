package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpotifyController {
    @GetMapping("/spotify")
    public String albums(){
        return "spotify/spotify-practice";
    }
}
