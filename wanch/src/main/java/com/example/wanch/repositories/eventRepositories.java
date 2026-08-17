package com.example.wanch.repositories;

import com.example.wanch.resources.Company;
import com.example.wanch.resources.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface eventRepositories extends JpaRepository<Event,Long> {
    @Query("SELECT e from Event e where e.eventOwner = :company")
    List<Event> getEventsByCompany(@Param("company") Company company);
}
