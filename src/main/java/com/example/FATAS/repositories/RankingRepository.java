package com.example.FATAS.repositories;

import com.example.FATAS.embeddable.RankingId;
import com.example.FATAS.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
    int countParticipantsByCompetitionCode(String competitionCode);
}
