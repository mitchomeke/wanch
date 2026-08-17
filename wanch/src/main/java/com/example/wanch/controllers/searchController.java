package com.example.wanch.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class searchController {

    @GetMapping("/wineSearch")
    public String wineSearch(){
        return "home";
    }
    @GetMapping("/cheesesearch")
    public String cheeseSearch(){
        return "home";
    }
}
