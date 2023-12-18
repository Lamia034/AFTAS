package com.example.FATAS.services.impl;

import com.example.FATAS.dtos.*;
import com.example.FATAS.embeddable.RankingId;
import com.example.FATAS.entities.*;
import com.example.FATAS.exceptions.ResourceNotFoundException;
import com.example.FATAS.exceptions.ResourceUnprocessableException;
import com.example.FATAS.repositories.*;
import com.example.FATAS.services.HuntingService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.Level;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final FishRepository fishRepository;
    private final CompetitionRepository competitionRepository;
    private final MemberRepository memberRepository;
    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;
    public HuntingServiceImpl(HuntingRepository huntingRepository, FishRepository fishRepository, CompetitionRepository competitionRepository,MemberRepository memberRepository,RankingRepository rankingRepository,ModelMapper modelMapper) {
        this.huntingRepository = huntingRepository;
        this.fishRepository = fishRepository;
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
        this.rankingRepository = rankingRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    @Transactional
    public HuntingResponseDto huntFish(HuntingDto huntingDto) {
        String fishName = huntingDto.getName();
        int numberOfFishes = huntingDto.getNumberOfFish();

        Optional<Fish> optionalFish = fishRepository.findById(fishName);
        if (optionalFish.isPresent()) {
            Fish fish = optionalFish.get();

            Optional<Hunting> optionalHunting = huntingRepository.findByFishNameAndCompetitionCodeAndMemberNum(
                    fishName,
                    huntingDto.getCode(),
                    huntingDto.getNum()
            );


            Hunting hunting;
            if (optionalHunting.isPresent()) {
                hunting = optionalHunting.get();
                hunting.setNumberOfFish(hunting.getNumberOfFish() + numberOfFishes);
            } else {
                hunting = new Hunting();
                hunting.setFish(fish);
                hunting.setNumberOfFish(numberOfFishes);

                Competition competition = new Competition();
                competition.setCode(huntingDto.getCode());
                hunting.setCompetition(competition);

                Member member = new Member();
                member.setNum(huntingDto.getNum());
                hunting.setMember(member);
            }

            huntingRepository.save(hunting);

            int levelPoints = fish.getLevel().getPoints();
            int score = numberOfFishes * levelPoints;
           // System.out.println("Code: " + huntingDto.getCode() + ", Num: " + huntingDto.getNum());
            RankingId key = new RankingId();
            key.setMemberNum(huntingDto.getNum());
            key.setCompetitionCode(huntingDto.getCode());
//            Optional<Ranking> optionalRanking = rankingRepository.findByCompetitionCodeAndMemberNum(huntingDto.getCode(), huntingDto.getNum());
            rankingRepository.findById(key).orElseThrow(() -> new ResourceNotFoundException("This member is not participated in this competition"));

            updateRankingScore(huntingDto, score);
            updateRanksForCompetition(huntingDto.getCode());



            return modelMapper.map(hunting, HuntingResponseDto.class);
        }
        return null;
    }






    @Transactional
    public RankingResponseDto updateRankingScore(HuntingDto huntingDto, int scoreToAdd) {
        Optional<Ranking> optionalRanking = rankingRepository.findByCompetitionCodeAndMemberNum(
                huntingDto.getCode(),
                huntingDto.getNum()
        );

        if (optionalRanking.isPresent()) {
            Ranking ranking = optionalRanking.get();
            int existingScore = ranking.getScore();
            int newScore = existingScore + scoreToAdd;


            ranking.setScore(newScore);

            Ranking updatedRanking = rankingRepository.save(ranking);

            return modelMapper.map(updatedRanking, RankingResponseDto.class);
        } else {
            throw new ResourceNotFoundException("Ranking not found for the provided competition and member");
        }
    }
    @Transactional
    public void updateRanksForCompetition(String competitionCode) {

        List<Ranking> rankingsForCompetition = rankingRepository.findByCompetitionCodeOrderByScoreDesc(competitionCode);

        if (!rankingsForCompetition.isEmpty()) {
            int rank = 0;
            int previousScore = -1;

            for (Ranking ranking : rankingsForCompetition) {
                int currentScore = ranking.getScore();
                if (currentScore != previousScore) {
                    rank++;
                }
                ranking.setRank(rank);
                rankingRepository.save(ranking);
                previousScore = currentScore;
            }
        }
    }



    @Override
    @Transactional
    public List<HuntingResponseDto> getAllHuntings(Pageable pageable) {
        try {
            Page<Hunting> huntingPage = huntingRepository.findAll(pageable);
            return huntingPage.stream()
                    .map(hunting -> modelMapper.map(hunting, HuntingResponseDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve huntings: " + e.getMessage());
        }
    }




    @Override
    @Transactional
    public boolean deleteHuntingById(Integer huntingId) {
        try {
            Optional<Hunting> huntingOptional = huntingRepository.findById(huntingId);
            if (huntingOptional.isPresent()) {
                huntingRepository.delete(huntingOptional.get());
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            throw new RuntimeException("Failed to delete huntings: " + e.getMessage());
        }
    }
}
