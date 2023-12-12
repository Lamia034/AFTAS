package com.example.FATAS.services;

import com.example.FATAS.dtos.CompetitionDto;
import com.example.FATAS.dtos.CompetitionResponseDto;
import com.example.FATAS.dtos.LevelDto;
import com.example.FATAS.dtos.LevelResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LevelService {
    LevelResponseDto saveLevel(LevelDto levelDto);
    //   List<LevelResponseDto> getAllLevels();
    List<LevelResponseDto> getAllLevels(Pageable pageable);
    boolean deleteLevelById(Integer levelId);
    LevelResponseDto updateLevel(Integer levelId, LevelDto updatedLevelDto);
    }


