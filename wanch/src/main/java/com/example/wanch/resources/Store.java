package com.example.wanch.resources;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Store {
    @Id
    @GeneratedValue
    private Long id;

    public Store(){}

    public Long getId() {
        return id;
    }
}
