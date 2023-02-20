package com.example.integrationbileter.repository;

import com.example.integrationbileter.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepo extends JpaRepository<ShowEntity, Integer> {
}
