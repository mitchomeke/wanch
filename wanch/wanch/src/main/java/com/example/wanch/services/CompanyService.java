package com.example.wanch.services;
import com.example.wanch.repositories.*;
import com.example.wanch.resources.Company;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final companyRepositories compRepo;
    private final PasswordEncoder passwordEncoder;


    public CompanyService(companyRepositories compRepo, PasswordEncoder passwordEncoder) {
        this.compRepo = compRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public void registerNewCompany(String password, String companyName){
        Company company = new Company(companyName);
        String encryptedPassword = passwordEncoder.encode(password);
        company.setEncryptedPassword(encryptedPassword);
        compRepo.save(company);
    }
}
