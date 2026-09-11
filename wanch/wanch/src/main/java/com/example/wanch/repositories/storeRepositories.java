package com.example.wanch.repositories;

import com.example.wanch.resources.Store;
import com.example.wanch.resources.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface storeRepositories extends JpaRepository<Store,Long> {
}
