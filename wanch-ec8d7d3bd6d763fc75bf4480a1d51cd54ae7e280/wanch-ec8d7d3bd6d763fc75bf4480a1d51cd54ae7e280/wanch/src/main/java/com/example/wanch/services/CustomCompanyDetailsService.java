package com.example.wanch.services;
import com.example.wanch.repositories.*;

import com.example.wanch.resources.Company;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomCompanyDetailsService implements UserDetailsService {
    private final companyRepositories compRepo;

    public CustomCompanyDetailsService(companyRepositories compRepo) {
        this.compRepo = compRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String companyName) throws UsernameNotFoundException {
        Company company = compRepo.findByCompanyName(companyName);
        return new org.springframework.security.core.userdetails.User(company.getCompanyName(),company.getEncryptedPassword(), Collections.emptyList());
    }
}
