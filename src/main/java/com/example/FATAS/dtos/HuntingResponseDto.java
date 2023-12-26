package com.example.FATAS.dtos;

import lombok.Data;

@Data
public class HuntingResponseDto {
    private Integer id;
    private Integer numberOfFish;
    private CompetitionDto competition;//competition
    private MemberDto member;//member
    private FishResponseDto fish;//fish
}
