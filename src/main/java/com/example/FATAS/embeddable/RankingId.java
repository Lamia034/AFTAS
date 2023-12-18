package com.example.FATAS.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class RankingId implements Serializable {
    @Column(name = "code")
    private String competitionCode;

    @Column(name = "num")
    private Integer memberNum;
}
