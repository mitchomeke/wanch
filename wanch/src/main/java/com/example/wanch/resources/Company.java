package com.example.wanch.resources;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Company{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String companyName;

    private String encryptedPassword;

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

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public Long getId() {
        return id;
    }
}
