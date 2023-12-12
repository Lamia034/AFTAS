package com.example.FATAS.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.*;
import lombok.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity

public class Competition {
    @Id
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Member> members;
    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Ranking> rankings;
    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Hunting> huntings;
}
