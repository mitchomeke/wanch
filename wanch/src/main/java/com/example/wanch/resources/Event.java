package com.example.wanch.resources;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    private String eventName;
    private Instant eventDate;
    @ManyToMany
    private List<Wine> wineList = new ArrayList<>();
    @ManyToMany
    private List<Cheese> cheeseList = new ArrayList<>();

    @ElementCollection
    Map<Wine,HashMap<Cheese,Integer>> compatibleList = new HashMap<>();

    @ManyToOne
    private Company eventOwner;
    public Event(){}
    public Event(String eventName,Company company){
        this.eventName = eventName;
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
    public Instant getEventDate() {
        return eventDate;
    }
    public void setEventDate(Instant eventDate) {
        this.eventDate = eventDate;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setEventOwner(Company eventOwner) {
        this.eventOwner = eventOwner;
    }
    public Long getId() {
        return id;
    }
    public HashMap<Wine, Map<Cheese,Integer>> compatibleCombinations(){
        initializeWines();
        HashMap<Wine,Map<Cheese,Integer>> compatibleStuff = new HashMap<>();
        for (Wine wine : wineList){
            for (Cheese cheese : cheeseList){
                if (wine.getListOfCompatibleCheese().get(cheese) != null){
                    HashMap<Cheese,Integer> map = compatibleList.get(wine);
                    Map.Entry<Cheese,Integer> entry = wine.getEntryOfCheese(cheese);
                    map.put(entry.getKey(),entry.getValue());
                    compatibleStuff.put(wine, map);
                }
            }
        }
        sortCompatibles(compatibleStuff);
        return compatibleStuff;
    }

    private void sortCompatibles(HashMap<Wine, Map<Cheese, Integer>> compatibleList) {
        for (Map.Entry<Wine, Map<Cheese, Integer>> entry : compatibleList.entrySet()) {
            Map<Cheese, Integer> sortedCheeses = entry.getValue().entrySet()
                    .stream()
                    .sorted(Map.Entry.<Cheese, Integer>comparingByValue().reversed())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));
            compatibleList.put(entry.getKey(), sortedCheeses);
        }
    }

    private void initializeWines(){
        for (Wine wine : wineList){
            compatibleList.put(wine,new HashMap<>());
        }
    }

    public Map<Wine, HashMap<Cheese, Integer>> getCompatibleList() {
        return compatibleList;
    }

    public void setCompatibleList(Map<Wine, HashMap<Cheese, Integer>> compatibleList) {
        this.compatibleList = compatibleList;
    }
}
