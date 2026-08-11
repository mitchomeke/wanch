package com.example.wanch.resources;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Company{
    @Id
    @GeneratedValue
    private Long id;

    private String companyName;

    private List<Event> events = new ArrayList<>();

    public Company(String companyName){
        this.companyName = companyName;
    }
    public Company(){}

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event){
        events.add(event);
    }
}
