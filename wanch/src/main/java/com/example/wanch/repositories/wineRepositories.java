package com.example.wanch.repositories;

import com.example.wanch.resources.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface wineRepositories extends JpaRepository<Wine,Long> {
}
