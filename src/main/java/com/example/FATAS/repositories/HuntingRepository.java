package com.example.FATAS.repositories;

import com.example.FATAS.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HuntingRepository extends JpaRepository<Hunting,Integer> {
    Hunting findByFishName(String fishName);
}
