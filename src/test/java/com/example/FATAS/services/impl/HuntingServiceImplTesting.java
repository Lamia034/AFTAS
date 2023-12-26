package com.example.FATAS.services.impl;

import com.example.FATAS.dtos.HuntingDto;
import com.example.FATAS.dtos.HuntingResponseDto;
import com.example.FATAS.embeddable.RankingId;
import com.example.FATAS.entities.*;
import com.example.FATAS.exceptions.ResourceNotFoundException;
import com.example.FATAS.repositories.FishRepository;
import com.example.FATAS.repositories.HuntingRepository;
import com.example.FATAS.repositories.RankingRepository;
import com.example.FATAS.services.HuntingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


//@RunWith(MockitoJUnitRunner.class)
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class HuntingServiceImplTesting {
    @Mock
    private FishRepository fishRepository;

    @Mock
    private HuntingRepository huntingRepository;

    @Mock
    private RankingRepository rankingRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private HuntingServiceImpl huntingService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHuntFish() {

        String fishName = "Salmon";
        int numberOfFishes = 5;
        String competitionCode = "lam-19-12-23";
        int memberNum = 53;

        HuntingDto huntingDto = new HuntingDto();
        huntingDto.setName(fishName);
        huntingDto.setNumberOfFish(numberOfFishes);
        huntingDto.setCode(competitionCode);
        huntingDto.setNum(memberNum);

        Fish fish = new Fish();
        fish.setName(fishName);
        fish.setLevel(new Level());
        fish.getLevel().setPoints(10);

        Hunting hunting = new Hunting();
        hunting.setFish(fish);
        hunting.setNumberOfFish(numberOfFishes);

        Competition competition = new Competition();
        competition.setCode(competitionCode);
        hunting.setCompetition(competition);

        Member member = new Member();
        member.setNum(memberNum);
        hunting.setMember(member);

     //   Ranking ranking = new Ranking();

        Mockito.when(fishRepository.findById(fishName)).thenReturn(Optional.of(fish));
        Mockito.when(huntingRepository.findByFishNameAndCompetitionCodeAndMemberNum(fishName, competitionCode, memberNum))
                .thenReturn(Optional.of(hunting));

        Mockito.when(rankingRepository.findById(Mockito.any(RankingId.class)))
                .thenThrow(new ResourceNotFoundException("Ranking not found for the provided competition and member"));

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            huntingService.huntFish(huntingDto);
        });

        Assertions.assertEquals("Ranking not found for the provided competition and member", exception.getMessage());
    }

    }

