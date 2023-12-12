package com.example.FATAS.dtos;

import com.example.FATAS.entities.Level;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class FishDto {
    private String name;//id
    private Double averageWeight;

    private Integer code;//level
}
