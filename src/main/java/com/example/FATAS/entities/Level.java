package com.example.FATAS.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Level {
    @Id
    private Integer code;
    private String description;
    private Integer points;
}
