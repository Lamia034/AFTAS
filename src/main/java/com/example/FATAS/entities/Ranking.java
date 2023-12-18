package com.example.FATAS.entities;

import com.example.FATAS.embeddable.RankingId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ranking {
    @EmbeddedId
    private RankingId id;
    private Integer rank;
    private Integer score;
    @ManyToOne
    @MapsId("competitionCode")
    @JoinColumn(name = "code", referencedColumnName = "code", insertable = false)
    private Competition competition;

    @ManyToOne
    @MapsId("memberNum")
    @JoinColumn(name = "num", referencedColumnName = "num", insertable = false)
    private Member member;
}
