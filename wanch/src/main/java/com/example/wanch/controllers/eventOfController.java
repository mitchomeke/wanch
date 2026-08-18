package com.example.wanch.controllers;

import com.example.wanch.repositories.companyRepositories;
import com.example.wanch.repositories.eventRepositories;
import com.example.wanch.resources.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class eventOfController {
    private final eventRepositories eventRepository;
    private final companyRepositories companyRepository;


    public eventOfController(eventRepositories eventRepository, companyRepositories companyRepository) {
        this.eventRepository = eventRepository;
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
}
