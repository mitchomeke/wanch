package com.example.wanch.repositories;

import com.example.wanch.resources.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface eventRepositories extends JpaRepository<Event,Long> {
}
