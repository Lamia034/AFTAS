package com.example.FATAS.services.impl;

import com.example.FATAS.dtos.CompetitionResponseDto;
import com.example.FATAS.dtos.FishResponseDto;
import com.example.FATAS.dtos.HuntingDto;
import com.example.FATAS.dtos.HuntingResponseDto;
import com.example.FATAS.entities.Fish;
import com.example.FATAS.entities.Hunting;
import com.example.FATAS.exceptions.ResourceNotFoundException;
import com.example.FATAS.exceptions.ResourceUnprocessableException;
import com.example.FATAS.repositories.FishRepository;
import com.example.FATAS.repositories.HuntingRepository;
import com.example.FATAS.services.HuntingService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;
    public HuntingServiceImpl(HuntingRepository huntingRepository, FishRepository fishRepository,ModelMapper modelMapper) {
        this.huntingRepository = huntingRepository;
        this.fishRepository = fishRepository;
        this.modelMapper = modelMapper;
    }
@Override
@Transactional
public HuntingResponseDto huntFish(HuntingDto huntingDto, double huntedFishWeight) {
    String fishName = huntingDto.getName();

    Optional<Fish> optionalFish = fishRepository.findById(fishName);
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
            return modelMapper.map(hunting, HuntingResponseDto.class);
        } else {
            throw new ResourceUnprocessableException("Fish not accountable: " + fishName);
        }
    } else {
        throw new ResourceNotFoundException("Fish not found with name: " + fishName);
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
