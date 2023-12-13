package com.example.FATAS.services;

import com.example.FATAS.dtos.FishDto;
import com.example.FATAS.dtos.FishResponseDto;
import com.example.FATAS.dtos.HuntingDto;
import com.example.FATAS.dtos.HuntingResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HuntingService {
    HuntingResponseDto huntFish(HuntingDto huntingDto, double huntedFishWeight);
    //   List<HuntingResponseDto> getAllHuntings();
    List<HuntingResponseDto> getAllHuntings(Pageable pageable);
    boolean deleteHuntingById(Integer huntingId);
//    HuntingResponseDto updateHunting(String huntingId, HuntingDto updatedHuntingDto);
}
