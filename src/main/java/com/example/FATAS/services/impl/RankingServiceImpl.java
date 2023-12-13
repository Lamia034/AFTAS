package com.example.FATAS.services.impl;

import com.example.FATAS.dtos.RankingDto;
import com.example.FATAS.dtos.RankingResponseDto;
import com.example.FATAS.embeddable.RankingId;
import com.example.FATAS.entities.Competition;
import com.example.FATAS.entities.Level;
import com.example.FATAS.entities.Member;
import com.example.FATAS.entities.Ranking;
import com.example.FATAS.exceptions.ResourceAlreadyExistException;
import com.example.FATAS.exceptions.ResourceNotFoundException;
import com.example.FATAS.exceptions.ResourceUnprocessableException;
import com.example.FATAS.repositories.CompetitionRepository;
import com.example.FATAS.repositories.LevelRepository;
import com.example.FATAS.repositories.MemberRepository;
import com.example.FATAS.repositories.RankingRepository;
import com.example.FATAS.services.LevelService;
import com.example.FATAS.services.RankingService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RankingServiceImpl implements RankingService {
    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    public RankingServiceImpl(RankingRepository rankingRepository, MemberRepository memberRepository,
                          CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.rankingRepository = rankingRepository;
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public RankingResponseDto saveRanking(@Valid RankingDto rankingDto) {

        RankingId rankingId = new RankingId();
        rankingId.setCompetitionCode(rankingDto.getCode());
        rankingId.setMemberNum(rankingDto.getNum());

        Member member = memberRepository.findById(rankingDto.getNum())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found."));

        Competition competition = competitionRepository.findById(rankingDto.getCode())
                .orElseThrow(() -> new ResourceNotFoundException("Competition not found."));

        if (rankingRepository.existsById(rankingId)) {
            throw new ResourceAlreadyExistException("Member \"" + member.getName() +
                    " " + member.getFamilyName() + "\" is already in this competition.");
        }

        int currentParticipants = rankingRepository.countParticipantsByCompetitionCode(rankingDto.getCode());

        if (currentParticipants >= competition.getNumberOfParticipants()) {
            throw new ResourceUnprocessableException("Maximum number of participants reached for this competition.");
        }

        LocalDateTime now = LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(now, competition.getStartTime());

        if (hours <= 24) {
            throw new ResourceUnprocessableException("You cannot assign new participants before 24h or less from the start of the competition.");
        }

        Ranking ranking = new Ranking();
        ranking.setId(rankingId);
        ranking.setMember(member);
        ranking.setCompetition(competition);

        Ranking createdRanking = rankingRepository.save(ranking);

        return modelMapper.map(createdRanking, RankingResponseDto.class);
    }



    @Override
    @Transactional
    public List<RankingResponseDto> getAllRankings(Pageable pageable) {
        try {
            Page<Ranking> rankingPage = rankingRepository.findAll(pageable);
            return rankingPage.stream()
                    .map(ranking -> modelMapper.map(ranking, RankingResponseDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve rankings: " + e.getMessage());
        }
    }




    @Override
    @Transactional
    public boolean deleteRankingById(RankingId rankingId) {
        try {
            Optional<Ranking> rankingOptional = rankingRepository.findById(rankingId);
            if (rankingOptional.isPresent()) {
                rankingRepository.delete(rankingOptional.get());
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            throw new RuntimeException("Failed to delete rankings: " + e.getMessage());
        }
    }
    @Override
    @Transactional
    public RankingResponseDto updateRanking(RankingId rankingId, RankingDto updatedRankingDto) {
        Optional<Ranking> optionalRanking = rankingRepository.findById(rankingId);
        if (optionalRanking.isPresent()) {
            Ranking existingRanking = optionalRanking.get();

            if (updatedRankingDto.getCode() != null) {
                Optional<Competition> competitionOptional = competitionRepository.findById(updatedRankingDto.getCode());
                if (competitionOptional.isPresent()) {
                    Competition competition = competitionOptional.get();
                    existingRanking.setCompetition(competition);
                } else {
                    throw new ResourceNotFoundException("Competition not found with code: " + updatedRankingDto.getCode());
                }
            }
            if (updatedRankingDto.getNum() != null) {
                Member member = memberRepository.findById(updatedRankingDto.getNum())
                        .orElseThrow(() -> new ResourceNotFoundException("Member not found."));
                existingRanking.setMember(member);
            }
            Ranking updatedRanking = rankingRepository.save(existingRanking);

            return modelMapper.map(updatedRanking, RankingResponseDto.class);
        } else {
            throw new ResourceNotFoundException("Ranking not found");
        }
    }

}
