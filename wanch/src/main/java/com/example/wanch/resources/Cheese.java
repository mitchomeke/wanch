package com.example.wanch.resources;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Cheese extends Store{
    private String cheeseName;
    @ElementCollection
    Map<Wine,Integer> compatibleWines = new HashMap<>();

    public Cheese(){}
    public Cheese(String cheeseName){
        this.cheeseName = cheeseName;
    }

    public String getCheeseName() {
        return cheeseName;
    }

    public void setCheeseName(String cheeseName) {
        this.cheeseName = cheeseName;
    }

    public Map<Wine,Integer> getCompatibleWines() {
        return compatibleWines;
    }

    public void setCompatibleWines(Map<Wine,Integer> compatibleWines) {
        this.compatibleWines = compatibleWines;
    }
    public void addCompatibleWine(Wine wine, Integer score){
        compatibleWines.put(wine,score);
    }
}
