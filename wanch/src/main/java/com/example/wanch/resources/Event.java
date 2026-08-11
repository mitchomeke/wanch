package com.example.wanch.resources;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    private String eventName;

    private List<Wine> wineList = new ArrayList<>();
    private List<Cheese> cheeseList = new ArrayList<>();

    @ManyToOne
    private Company eventOwner;
    public Event(){}
    public Event(String eventName,Company company){
        eventOwner = company;
    }

    public List<Wine> getWineList() {
        return wineList;
    }

    public void setWineList(List<Wine> wineList) {
        this.wineList = wineList;
    }

    public List<Cheese> getCheeseList() {
        return cheeseList;
    }

    public void setCheeseList(List<Cheese> cheeseList) {
        this.cheeseList = cheeseList;
    }

    public Company getEventOwner() {
        return eventOwner;
    }

    public void addCheese(Cheese cheese){
        cheeseList.add(cheese);
    }
    public void addWine(Wine wine){
        wineList.add(wine);
    }
}
