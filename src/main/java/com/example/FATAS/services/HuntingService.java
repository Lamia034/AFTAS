package com.example.FATAS.services;

import com.example.FATAS.dtos.FishDto;
import com.example.FATAS.dtos.FishResponseDto;
import com.example.FATAS.dtos.HuntingDto;
import com.example.FATAS.dtos.HuntingResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class HuntingService {
    HuntingResponseDto huntFish(HuntingDto huntingDto , String fishName, double huntedFishWeight);
    //   List<HuntingResponseDto> getAllHuntings();
    List<HuntingResponseDto> getAllHuntinges(Pageable pageable);
    boolean deleteHuntingById(String huntingId);
//    HuntingResponseDto updateHunting(String huntingId, HuntingDto updatedHuntingDto);
}
