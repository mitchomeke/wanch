package com.example.wanch.repositories;

import com.example.wanch.resources.Cheese;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cheeseRepositories extends JpaRepository<Cheese,Long> {
}
