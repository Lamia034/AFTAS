package com.example.FATAS.services.impl;

import com.example.FATAS.dtos.CompetitionDto;
import com.example.FATAS.dtos.CompetitionResponseDto;
import com.example.FATAS.entities.Competition;
import com.example.FATAS.exceptions.ResourceNotFoundException;
import com.example.FATAS.repositories.CompetitionRepository;
import com.example.FATAS.services.CompetitionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
    public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public CompetitionResponseDto saveCompetition(@Valid CompetitionDto competitionDto) {
        try {
            Competition competition = modelMapper.map(competitionDto, Competition.class);
            Competition savedCompetition = competitionRepository.save(competition);
            return modelMapper.map(savedCompetition, CompetitionResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save competition: " + e.getMessage());
        }
    }




    @Override
    @Transactional
    public List<CompetitionResponseDto> getAllCompetitions(Pageable pageable) {
        try {
            Page<Competition> competitionPage = competitionRepository.findAll(pageable);
            return competitionPage.stream()
                    .map(competition -> modelMapper.map(competition, CompetitionResponseDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve competitions: " + e.getMessage());
        }
    }
@Override
@Transactional
    public long getCompetitionsCount() {
        return competitionRepository.count();
    }




    @Override
    @Transactional
    public boolean deleteCompetitionById(String competitionId) {
        try {
            Optional<Competition> competitionOptional = competitionRepository.findById(competitionId);
            if (competitionOptional.isPresent()) {
                competitionRepository.delete(competitionOptional.get());
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            throw new RuntimeException("Failed to delete competitions: " + e.getMessage());
        }
    }
    @Override
    @Transactional
    public CompetitionResponseDto updateCompetition(String competitionId, CompetitionDto updatedCompetitionDto) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(competitionId);
        if (optionalCompetition.isPresent()) {
            Competition existingCompetition = optionalCompetition.get();

            if (updatedCompetitionDto.getDate() != null) {
                existingCompetition.setDate(updatedCompetitionDto.getDate());
            }
            if (updatedCompetitionDto.getStartTime() != null) {
                existingCompetition.setStartTime(updatedCompetitionDto.getStartTime());
            }
            if (updatedCompetitionDto.getEndTime() != null) {
                existingCompetition.setEndTime(updatedCompetitionDto.getEndTime());
            }
            if (updatedCompetitionDto.getNumberOfParticipants() != null) {
                existingCompetition.setNumberOfParticipants(updatedCompetitionDto.getNumberOfParticipants());
            }
            if (updatedCompetitionDto.getLocation() != null) {
                existingCompetition.setLocation(updatedCompetitionDto.getLocation());
            }
            if (updatedCompetitionDto.getAmount() != null) {
                existingCompetition.setAmount(updatedCompetitionDto.getAmount());
            }
            Competition updatedCompetition = competitionRepository.save(existingCompetition);

            return modelMapper.map(updatedCompetition, CompetitionResponseDto.class);
        } else {
            throw new ResourceNotFoundException("Competition not found");
        }
    }
}