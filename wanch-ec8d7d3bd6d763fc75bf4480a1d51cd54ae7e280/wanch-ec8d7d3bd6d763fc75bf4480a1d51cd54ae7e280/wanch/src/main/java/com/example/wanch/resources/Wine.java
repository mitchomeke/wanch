package com.example.wanch.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.apache.tomcat.util.collections.ManagedConcurrentWeakHashMap;

import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Wine extends Store{
    String wineName;
    WINETYPE winetype;
    @ElementCollection
    Map<Cheese,Integer> listOfCompatibleCheese = new HashMap<>();

    public Wine(String wineName, WINETYPE winetype){
        this.wineName = wineName;
        this.winetype = winetype;
    }

    public Wine() {}

    public Map<Cheese,Integer> getListOfCompatibleCheese() {
         return listOfCompatibleCheese.entrySet().stream().sorted(
                 Map.Entry.<Cheese,Integer>comparingByValue().reversed()
         ).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
                 (e1,e2) -> e1,
                 LinkedHashMap::new));
    }
    public Map.Entry<Cheese,Integer> getEntryOfCheese(Cheese cheese){
        for (Map.Entry<Cheese, Integer> entry : listOfCompatibleCheese.entrySet()) {
            if (entry.getKey().equals(cheese)) {
                return entry;
            }
        }
        return null;
    }

    public void setListOfCompatibleCheese(Map<Cheese,Integer> listOfCompatibleCheese) {
        this.listOfCompatibleCheese = listOfCompatibleCheese;
    }

    public String getWineName() {
        return wineName;
    }

    public String getWinetype() {
        return winetype.name();
    }
    public void addCompatibleCheese(Cheese cheese, Integer score){
        listOfCompatibleCheese.put(cheese,score);
    }
}
