package com.example.FATAS.services.impl;

import com.example.FATAS.dtos.FishDto;
import com.example.FATAS.dtos.FishResponseDto;
import com.example.FATAS.dtos.MemberResponseDto;
import com.example.FATAS.entities.Competition;
import com.example.FATAS.entities.Fish;
import com.example.FATAS.entities.Level;
import com.example.FATAS.entities.Member;
import com.example.FATAS.exceptions.ResourceNotFoundException;
import com.example.FATAS.repositories.FishRepository;
import com.example.FATAS.repositories.LevelRepository;
import com.example.FATAS.services.FishService;
import com.example.FATAS.services.LevelService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FishServiceImpl implements FishService {
    private final FishRepository fishRepository;
    private final ModelMapper modelMapper;
    private final LevelRepository levelRepository;

    @Autowired
    public FishServiceImpl(FishRepository fishRepository, ModelMapper modelMapper, LevelRepository levelRepository) {
        this.fishRepository = fishRepository;
        this.modelMapper = modelMapper;
        this.levelRepository = levelRepository;
    }

    @Override
    @Transactional
    public FishResponseDto saveFish(@Valid FishDto fishDto) {
        try {
            Fish fish = modelMapper.map(fishDto, Fish.class);
            Fish savedFish = fishRepository.save(fish);
            return modelMapper.map(savedFish, FishResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save fish: " + e.getMessage());
        }
    }




    @Override
    @Transactional
    public List<FishResponseDto> getAllFishes(Pageable pageable) {
        try {
            Page<Fish> fishPage = fishRepository.findAll(pageable);
            return fishPage.stream()
                    .map(fish -> modelMapper.map(fish, FishResponseDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve fishs: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public FishResponseDto getFishById(String fishId) {
        try {
            Optional<Fish> fishOptional = fishRepository.findById(fishId);
            if (fishOptional.isPresent()) {
                return modelMapper.map(fishOptional.get(), FishResponseDto.class);
            } else {
                throw new ResourceNotFoundException("Fish not found with ID: " + fishId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve fish: " + e.getMessage());
        }
    }


    @Override
    @Transactional
    public boolean deleteFishById(String fishId) {
        try {
            Optional<Fish> fishOptional = fishRepository.findById(fishId);
            if (fishOptional.isPresent()) {
                fishRepository.delete(fishOptional.get());
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            throw new RuntimeException("Failed to delete fishs: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public FishResponseDto updateFish(String fishId, FishDto updatedFishDto) {
        Optional<Fish> optionalFish = fishRepository.findById(fishId);
        if (optionalFish.isPresent()) {
            Fish existingFish = optionalFish.get();
            if (updatedFishDto.getName() != null) {
                existingFish.setName(updatedFishDto.getName());
            }
            if (updatedFishDto.getAverageWeight() != null) {
                existingFish.setAverageWeight(updatedFishDto.getAverageWeight());
            }
            if (updatedFishDto.getCode() != null) {
                Integer levelCode = updatedFishDto.getCode();
                Optional<Level> optionalLevel = levelRepository.findById(levelCode);
                if (optionalLevel.isPresent()) {
                    Level level = optionalLevel.get();
                    existingFish.setLevel(level);
                } else {
                    throw new ResourceNotFoundException("Level not found with code: " + levelCode);
                }
            }
            Fish updatedFish = fishRepository.save(existingFish);

            return modelMapper.map(updatedFish, FishResponseDto.class);
        } else {
            throw new ResourceNotFoundException("Fish not found");
        }
    }
}