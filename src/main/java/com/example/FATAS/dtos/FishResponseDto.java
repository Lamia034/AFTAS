package com.example.FATAS.dtos;

import com.example.FATAS.entities.Hunting;
import com.example.FATAS.entities.Level;
import lombok.Data;

import java.util.List;

@Data
public class FishResponseDto {
    private String name;//id
    private Double averageWeight;
    private LevelDto level;//level
    private List<HuntingDto> huntings;
}
