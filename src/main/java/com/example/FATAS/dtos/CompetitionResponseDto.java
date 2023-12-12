package com.example.FATAS.dtos;

import com.example.FATAS.entities.Hunting;
import com.example.FATAS.entities.Ranking;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CompetitionResponseDto {
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    private List<RankingDto> rankings;
    private List<HuntingDto> huntings;
}
