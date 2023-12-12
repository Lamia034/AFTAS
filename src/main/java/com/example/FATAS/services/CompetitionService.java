package com.example.FATAS.services;

import com.example.FATAS.dtos.CompetitionDto;
import com.example.FATAS.dtos.CompetitionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetitionService {
   CompetitionResponseDto saveCompetition(CompetitionDto competitionDto);
//   List<CompetitionResponseDto> getAllCompetitions();
List<CompetitionResponseDto> getAllCompetitions(Pageable pageable);
boolean deleteCompetitionById(String competitionId);
   CompetitionResponseDto updateCompetition(String competitionId, CompetitionDto updatedCompetitionDto);
}
