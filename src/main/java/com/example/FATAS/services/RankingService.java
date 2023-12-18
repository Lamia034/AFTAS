package com.example.FATAS.services;

import com.example.FATAS.dtos.LevelDto;
import com.example.FATAS.dtos.LevelResponseDto;
import com.example.FATAS.dtos.RankingDto;
import com.example.FATAS.dtos.RankingResponseDto;
import com.example.FATAS.embeddable.RankingId;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RankingService {
    RankingResponseDto saveRanking(RankingDto rankingDto);
    //   List<RankingResponseDto> getAllRankings();
    List<RankingResponseDto> getAllRankings(Pageable pageable);
    List<RankingResponseDto> getTopThreeCompetitors(String competitionCode);
    boolean deleteRankingById(RankingId rankingId);
    RankingResponseDto updateRanking(RankingId rankingId, RankingDto updatedRankingDto);
}
