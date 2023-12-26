package com.example.FATAS.dtos;

import lombok.Data;

import java.util.List;

@Data
public class LevelResponseDto {
    private Integer code;
    private String description;
    private Integer points;
//    private List<FishDto> fishes;
}
