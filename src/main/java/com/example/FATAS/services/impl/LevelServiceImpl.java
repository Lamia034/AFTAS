package com.example.FATAS.services.impl;

import com.example.FATAS.dtos.LevelDto;
import com.example.FATAS.dtos.LevelResponseDto;
import com.example.FATAS.entities.Level;
import com.example.FATAS.exceptions.ResourceNotFoundException;
import com.example.FATAS.repositories.CompetitionRepository;
import com.example.FATAS.repositories.LevelRepository;
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
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository, ModelMapper modelMapper) {
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public LevelResponseDto saveLevel(@Valid LevelDto levelDto) {
        try {
            Level level = modelMapper.map(levelDto, Level.class);
            Level savedLevel = levelRepository.save(level);
            return modelMapper.map(savedLevel, LevelResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save level: " + e.getMessage());
        }
    }




    @Override
    @Transactional
    public List<LevelResponseDto> getAllLevels(Pageable pageable) {
        try {
            Page<Level> levelPage = levelRepository.findAll(pageable);
            return levelPage.stream()
                    .map(level -> modelMapper.map(level, LevelResponseDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve levels: " + e.getMessage());
        }
    }




    @Override
    @Transactional
    public boolean deleteLevelById(Integer levelId) {
        try {
            Optional<Level> levelOptional = levelRepository.findById(levelId);
            if (levelOptional.isPresent()) {
                levelRepository.delete(levelOptional.get());
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            throw new RuntimeException("Failed to delete levels: " + e.getMessage());
        }
    }
    @Override
    @Transactional
    public LevelResponseDto updateLevel(Integer levelId, LevelDto updatedLevelDto) {
        Optional<Level> optionalLevel = levelRepository.findById(levelId);
        if (optionalLevel.isPresent()) {
            Level existingLevel = optionalLevel.get();

            if (updatedLevelDto.getDescription() != null) {
                existingLevel.setDescription(updatedLevelDto.getDescription());
            }
            if (updatedLevelDto.getPoints() != null) {
                existingLevel.setPoints(updatedLevelDto.getPoints());
            }
            Level updatedLevel = levelRepository.save(existingLevel);

            return modelMapper.map(updatedLevel, LevelResponseDto.class);
        } else {
            throw new ResourceNotFoundException("Level not found");
        }
    }
}
