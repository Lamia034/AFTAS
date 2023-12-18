package com.example.FATAS.repositories;

import com.example.FATAS.embeddable.RankingId;
import com.example.FATAS.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
    int countParticipantsByCompetitionCode(String competitionCode);

    Optional<Ranking> findByCompetitionCodeAndMemberNum(String competitionCode, Integer memberNum);

    List<Ranking> findAllByOrderByScoreDesc();

    Optional<Ranking> findByCompetitionCode(String code);

    List<Ranking> findByCompetitionCodeOrderByScoreDesc(String competitionCode);

    List<Ranking> findByCompetitionCodeOrderByRankAsc(String competitionCode);
}
