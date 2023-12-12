package com.example.FATAS.dtos;

import com.example.FATAS.embeddable.RankingId;
import lombok.Data;

@Data
public class RankingResponseDto {
    private RankingId id;
    private Integer rank;
    private Integer score;
    private CompetitionDto competition;
    private MemberDto member;
}
