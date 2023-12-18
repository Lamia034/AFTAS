package com.example.FATAS.services;

import com.example.FATAS.dtos.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FishService {
    FishResponseDto saveFish(FishDto fishDto);
    //   List<FishResponseDto> getAllFishs();
    List<FishResponseDto> getAllFishes(Pageable pageable);
    FishResponseDto getFishById(String fishId);
    boolean deleteFishById(String fishId);
    FishResponseDto updateFish(String fishId, FishDto updatedFishDto);
    }


