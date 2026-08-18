package com.example.wanch.controllers;
import com.example.wanch.repositories.*;

import com.example.wanch.resources.Company;
import com.example.wanch.resources.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class eventsController {
    private final eventRepositories eventRepository;
    private final companyRepositories companyRepository;


    public eventsController(eventRepositories eventRepository, companyRepositories companyRepository) {
        this.eventRepository = eventRepository;
        this.companyRepository = companyRepository;
    }
    @GetMapping("/events")
    public String eventsByCompany(@RequestParam(required = true) Long companyId, Model model){
        Company company = companyRepository.findById(companyId).orElseThrow();
        List<Event> events = eventRepository.getEventsByCompany(company);

        model.addAttribute("company",company);
        model.addAttribute("events",events);
        return "allEvents";
    }

}
