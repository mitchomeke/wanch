package com.example.wanch.repositories;

import com.example.wanch.resources.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface companyRepositories extends JpaRepository<Company, Long> {
}
