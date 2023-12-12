package com.example.FATAS.services;

import com.example.FATAS.dtos.FishDto;
import com.example.FATAS.dtos.FishResponseDto;
import com.example.FATAS.dtos.LevelDto;
import com.example.FATAS.dtos.LevelResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FishService {
    FishResponseDto saveFish(FishDto fishDto);
    //   List<FishResponseDto> getAllFishs();
    List<FishResponseDto> getAllFishes(Pageable pageable);
    boolean deleteFishById(String fishId);
    FishResponseDto updateFish(String fishId, FishDto updatedFishDto);
    }


