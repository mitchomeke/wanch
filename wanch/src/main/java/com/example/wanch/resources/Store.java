package com.example.wanch.resources;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue
    private Long id;

    private List<Wine> winesAvailable;
    private List<Cheese> cheeseAvailable;

    public Store(){}

    public List<Wine> getWinesAvailable() {
        return winesAvailable;
    }

    public void setWinesAvailable(List<Wine> winesAvailable) {
        this.winesAvailable = winesAvailable;
    }

    public List<Cheese> getCheeseAvailable() {
        return cheeseAvailable;
    }

    public void setCheeseAvailable(List<Cheese> cheeseAvailable) {
        this.cheeseAvailable = cheeseAvailable;
    }
    public void addCheese(Cheese cheese){
        cheeseAvailable.add(cheese);
    }
    public void addWine(Wine wine){
        winesAvailable.add(wine);
    }
}
