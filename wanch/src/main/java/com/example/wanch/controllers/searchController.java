package com.example.wanch.controllers;
import com.example.wanch.repositories.*;
import com.example.wanch.resources.Cheese;
import com.example.wanch.resources.Wine;
import com.example.wanch.services.CheeseService;
import com.example.wanch.services.WineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class searchController {
    private final WineService wineService;
    private final CheeseService cheeseService;
    private final cheeseRepositories cheeseRepository;
    private final wineRepositories wineRepository;

    public searchController(WineService wineService, CheeseService cheeseService, wineRepositories wineRepository, cheeseRepositories cheeseRepository, cheeseRepositories cheeseRepository1, wineRepositories wineRepository1) {
        this.wineService = wineService;
        this.cheeseService = cheeseService;
        this.cheeseRepository = cheeseRepository1;
        this.wineRepository = wineRepository1;
    }

    @GetMapping("/search")
    public String Search(@RequestParam(value = "wineId", required = false) Long wineId, @RequestParam(value = "cheeseId", required = false) Long cheeseId, Model model){
        if (wineId != null){
            return wineSearch(wineId,model);
        } else {
            return cheeseSearch(cheeseId,model);
        }
    }
    private String wineSearch(Long wineId,Model model){
        Wine wine = wineRepository.findById(wineId).orElseThrow();
        Map<Cheese,Integer> compatibleCheese = wine.getListOfCompatibleCheese();
        model.addAttribute("wine",wine);
        model.addAttribute("compatibleCheese",compatibleCheese);
        model.addAttribute("wineDescription",wineService.getWineDescription(wine.getWineName()));
        return "wine";
    }
    private String cheeseSearch(Long cheeseId, Model model){
        Cheese cheese = cheeseRepository.findById(cheeseId).orElseThrow();
        Map<Wine,Integer> compatibleWine = cheese.getCompatibleWines();
        model.addAttribute("cheese",cheese);
        model.addAttribute("compatibleWine",compatibleWine);
        model.addAttribute("cheeseDescription",cheeseService.getCheeseDescription(cheese.getCheeseName()));
        return "cheese";
    }
}
