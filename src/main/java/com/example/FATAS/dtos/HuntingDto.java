package com.example.FATAS.dtos;

import com.example.FATAS.entities.Competition;
import com.example.FATAS.entities.Fish;
import com.example.FATAS.entities.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class HuntingDto {
    private Integer id;
    private Integer numberOfFish;
    private String code;//competition
    private Integer num;//member
    private String name;//fish
}
