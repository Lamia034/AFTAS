package com.example.FATAS.services.impl;

import com.example.FATAS.dtos.FishResponseDto;
import com.example.FATAS.dtos.HuntingResponseDto;
import com.example.FATAS.entities.Fish;
import com.example.FATAS.entities.Hunting;
import com.example.FATAS.exceptions.ResourceNotFoundException;
import com.example.FATAS.repositories.FishRepository;
import com.example.FATAS.repositories.HuntingRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HuntingServiceImpl {

    private final HuntingRepository huntingRepository;
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;
    public HuntingServiceImpl(HuntingRepository huntingRepository, FishRepository fishRepository,ModelMapper modelMapper) {
        this.huntingRepository = huntingRepository;
        this.fishRepository = fishRepository;
        this.modelMapper = modelMapper;
    }

    public void huntFish(String fishName, double huntedFishWeight) {
        Optional<Fish> optionalFish = fishRepository.findById(fishName);
        if (optionalFish.isPresent()) {
            if (optionalFish.isPresent()) {
                Fish fish = optionalFish.get();
                if (huntedFishWeight >= fish.getAverageWeight()) {
                    Hunting hunting = huntingRepository.findByFishName(fishName);
                    if (hunting != null) {
                        hunting.setNumberOfFish(hunting.getNumberOfFish() + 1);
                    } else {
                        hunting = new Hunting();
                        hunting.setFish(fish);
                        hunting.setNumberOfFish(1);
                    }
                    huntingRepository.save(hunting);
                }
            } else {
                throw new ResourceNotFoundException("Fish not found with code: " + fishName);
            }
        }
    }


    @Override
    @Transactional
    public List<HuntingResponseDto> getAllHuntings(Pageable pageable) {
        try {
            Page<Hunting> huntingPage = huntingRepository.findAll(pageable);
            return huntingPage.stream()
                    .map(hunting -> modelMapper.map(hunting, HuntingResponseDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve huntings: " + e.getMessage());
        }
    }




    @Override
    @Transactional
    public boolean deleteHuntingById(Integer huntingId) {
        try {
            Optional<Hunting> huntingOptional = huntingRepository.findById(huntingId);
            if (huntingOptional.isPresent()) {
                huntingRepository.delete(huntingOptional.get());
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            throw new RuntimeException("Failed to delete huntings: " + e.getMessage());
        }
    }
}
