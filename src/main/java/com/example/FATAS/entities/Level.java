package com.example.FATAS.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Level {
    @Id
    private Integer code;
    private String description;
    private Integer points;
}
