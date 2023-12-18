package com.example.FATAS.repositories;

import com.example.FATAS.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HuntingRepository extends JpaRepository<Hunting,Integer> {
    Hunting findByFishName(String fishName);

    Optional<Hunting> findByFishNameAndCompetitionCodeAndMemberNum(String fishName, String code, Integer num);
}
