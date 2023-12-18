package com.example.FATAS.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Hunting {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer numberOfFish;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "code")
    private Competition competition;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "num")
    private Member member;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "name")
    private Fish fish;

}
