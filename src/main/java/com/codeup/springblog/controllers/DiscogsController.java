package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscogsController {

    @GetMapping("/albums")
    public String albums(){
        return "discogsTest/discogs-demo";
    }
}
