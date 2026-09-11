package com.example.wanch.controllers;
import com.example.wanch.repositories.*;

import com.example.wanch.resources.Cheese;
import com.example.wanch.resources.Company;
import com.example.wanch.resources.Event;
import com.example.wanch.resources.Wine;
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
public class eventsController {
    private final eventRepositories eventRepository;
    private final companyRepositories companyRepository;
    private final wineRepositories wineRepository;
    private final cheeseRepositories cheeseRepository;


    public eventsController(eventRepositories eventRepository, companyRepositories companyRepository, wineRepositories wineRepository, cheeseRepositories cheeseRepository) {
        this.eventRepository = eventRepository;
        this.companyRepository = companyRepository;
        this.wineRepository = wineRepository;
        this.cheeseRepository = cheeseRepository;
    }
    @GetMapping("/events")
    public String eventsByCompany(@RequestParam(required = true) Long companyId, Model model){
        Company company = companyRepository.findById(companyId).orElseThrow();
        List<Event> events = eventRepository.getEventsByCompany(company);

        model.addAttribute("allWinesInStore",wineRepository.findAll().stream().toList());
        model.addAttribute("allCheeseInStore",cheeseRepository.findAll().stream().toList());
        model.addAttribute("company",company);
        model.addAttribute("events",events);
        return "allEvents";
    }

    @PostMapping("/editEvent")
    public String createEvent(Principal principal, @RequestParam("eventName")
                              String eventName,@RequestParam("eventId") Long eventId, @RequestParam("wines") List<Long> wineId,
                              @RequestParam("cheeses")List<Long> cheeseId, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate){
        Company company = companyRepository.findByCompanyName(principal.getName());
        Event event = eventRepository.findById(eventId).orElseThrow();

        List<Wine> wines = wineRepository.findAllById(wineId);
        List<Cheese> cheeses = cheeseRepository.findAllById(cheeseId);

        Instant dateInstant = eventDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        event.setEventName(eventName);
        event.setEventDate(dateInstant);
        event.setWineList(wines);
        event.setCheeseList(cheeses);

        eventRepository.save(event);
        companyRepository.save(company);

        return "redirect:/events?companyId="+company.getId();
    }
    @PostMapping("/deleteEvent")
    public String deleteEvent(@RequestParam("eventId") Long eventId, Principal principal){
        Company company = companyRepository.findByCompanyName(principal.getName());
        eventRepository.deleteById(eventId);
        return "redirect:/events?companyId="+company.getId();
    }



}
