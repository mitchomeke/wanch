package com.example.wanch.controllers;
import com.example.wanch.*;
import com.example.wanch.repositories.companyRepositories;
import com.example.wanch.repositories.*;
import com.example.wanch.resources.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Controller
public class homeController {

    private final companyRepositories companyRepository;
    private final eventRepositories eventRepositories;
    private final wineRepositories wineRepositories;
    private final cheeseRepositories cheeseRepositories;

    public homeController(companyRepositories companyRepository, eventRepositories eventRepositories, wineRepositories wineRepositories, cheeseRepositories cheeseRepositories) {
        this.companyRepository = companyRepository;
        this.eventRepositories = eventRepositories;
        this.wineRepositories = wineRepositories;
        this.cheeseRepositories = cheeseRepositories;
    }

    @GetMapping("/home")
    public String getHomePage(Principal principal, Model model){
        String companyName = principal.getName();
        Company company = companyRepository.findByCompanyName(companyName);
        List<Wine> allWineInStore = wineRepositories.findAll().stream().toList();
        List<Cheese> allCheeseInStore = cheeseRepositories.findAll().stream().toList();

        model.addAttribute("company",company);
        model.addAttribute("allWinesInStore",allWineInStore);
        model.addAttribute("allCheeseInStore",allCheeseInStore);
        return "home";
    }
    @PostMapping("/createEvent")
    public String createEvent(Principal principal, @RequestParam("eventName")
                              String eventName, @RequestParam("wines") List<Long> wineId,
                              @RequestParam("cheeses")List<Long> cheeseId, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate){
        Company company = companyRepository.findByCompanyName(principal.getName());
        Event event = new Event(eventName,company);

        List<Wine> wines = wineRepositories.findAllById(wineId);;
        List<Cheese> cheeses = cheeseRepositories.findAllById(cheeseId);

        Instant dateInstant = eventDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        event.setEventDate(dateInstant);
        event.setWineList(wines);
        event.setCheeseList(cheeses);

        eventRepositories.save(event);
        companyRepository.save(company);

        return "redirect:/home";
    }
}
