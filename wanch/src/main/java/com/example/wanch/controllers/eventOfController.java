package com.example.wanch.controllers;

import com.example.wanch.repositories.*;
import com.example.wanch.repositories.eventRepositories;
import com.example.wanch.resources.Cheese;
import com.example.wanch.resources.Company;
import com.example.wanch.resources.Event;
import com.example.wanch.resources.Wine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class eventOfController {
    private final eventRepositories eventRepository;
    private final wineRepositories wineRepository;
    private final cheeseRepositories cheeseRepository;
    private final companyRepositories companyRepository;


    public eventOfController(eventRepositories eventRepository, wineRepositories wineRepository, cheeseRepositories cheeseRepository, companyRepositories companyRepository) {
        this.eventRepository = eventRepository;
        this.wineRepository = wineRepository;
        this.cheeseRepository = cheeseRepository;
        this.companyRepository = companyRepository;
    }
    @GetMapping("/eventOf")
    public String eventOf(@RequestParam(required = true) Long eventId, Model model){
        Event event = eventRepository.findById(eventId).orElseThrow();

        model.addAttribute("event",event);
        model.addAttribute("compatibleCombinations",event.compatibleCombinations());
        model.addAttribute("company",event.getEventOwner());
        return "particularEvent";
    }
    @PostMapping("/deletePairing")
    public String deletePairing(@RequestParam(required = true) Long eventId, @RequestParam(required = true) Long wineId){
        Event event = eventRepository.findById(eventId).orElseThrow();
        Company company = event.getEventOwner();
        event.deletePairing(wineId);
        eventRepository.save(event);
        companyRepository.save(company);
        return "redirect:/eventOf?eventId="+eventId;
    }
}
