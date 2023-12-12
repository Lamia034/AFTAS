package com.example.FATAS.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Fish {
    @Id
    private String name;
    private Double averageWeight;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "code")
    private Level level;
    @OneToMany(mappedBy = "fish", cascade = CascadeType.ALL)
    private List<Hunting> huntings;

}
