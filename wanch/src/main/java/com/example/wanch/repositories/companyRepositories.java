package com.example.wanch.repositories;

import com.example.wanch.resources.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface companyRepositories extends JpaRepository<Company, Long> {
    @Query("SELECT c from Company c where c.companyName = :companyName")
    Company findByCompanyName(@Param("companyName") String companyName);
}
