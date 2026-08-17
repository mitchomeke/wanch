package com.example.wanch.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Wine extends Store{
    String wineName;
    WINETYPE winetype;
    @ManyToMany
    List<Cheese> listOfCompatibleCheese = new ArrayList<>();

    public Wine(String wineName, WINETYPE winetype){
        this.wineName = wineName;
        this.winetype = winetype;
    }

    public Wine() {}

    public List<Cheese> getListOfCompatibleCheese() {
        return listOfCompatibleCheese;
    }

    public void setListOfCompatibleCheese(List<Cheese> listOfCompatibleCheese) {
        this.listOfCompatibleCheese = listOfCompatibleCheese;
    }

    public String getWineName() {
        return wineName;
    }

    public String getWinetype() {
        return winetype.name();
    }
}
