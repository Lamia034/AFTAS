package com.example.FATAS.repositories;

import com.example.FATAS.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, String> {

}
