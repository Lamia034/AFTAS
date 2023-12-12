package com.example.FATAS.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class RankingId implements Serializable {
    private String competitionCode;
    private Integer memberNum;

}
