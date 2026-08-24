package com.example.wanch.resources;

import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

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
      return compatibleWines.entrySet()
              .stream()
              .sorted(Map.Entry.<Wine,Integer>comparingByValue().reversed())
              .collect(Collectors.toMap
                      (Map.Entry::getKey,
                      Map.Entry::getValue,
                              (e1,e2) -> e1,
                                LinkedHashMap::new));
    }

    public void setCompatibleWines(Map<Wine,Integer> compatibleWines) {
        this.compatibleWines = compatibleWines;
    }
    public void addCompatibleWine(Wine wine, Integer score){
        compatibleWines.put(wine,score);
    }
}
