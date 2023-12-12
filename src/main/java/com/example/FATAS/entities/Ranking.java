package com.example.FATAS.entities;

import com.example.FATAS.embeddable.RankingId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Ranking {
    @EmbeddedId
    private RankingId id;
    private Integer rank;
    private Integer score;
    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code", insertable = false)
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "num", referencedColumnName = "num", insertable = false)
    private Member member;
}
