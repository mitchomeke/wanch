package com.example.wanch.resources;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cheese extends Store{
    private String cheeseName;
    @ManyToMany
    List<Wine> compatibleWines = new ArrayList<>();

    public Cheese(){}
    public Cheese(String cheeseName, List<Wine> wines){
        this.cheeseName = cheeseName;
        compatibleWines = wines;
    }

    public String getCheeseName() {
        return cheeseName;
    }

    public void setCheeseName(String cheeseName) {
        this.cheeseName = cheeseName;
    }

    public List<Wine> getCompatibleWines() {
        return compatibleWines;
    }

    public void setCompatibleWines(List<Wine> compatibleWines) {
        this.compatibleWines = compatibleWines;
    }
}
