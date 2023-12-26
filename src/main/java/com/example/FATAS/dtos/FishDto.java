package com.example.FATAS.dtos;

import com.example.FATAS.entities.Level;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FishDto {
    @NotBlank(message = "fish name is necessary")
    private String name;//id
    @NotNull(message = "average weight imprtant")
    private Double averageWeight;
    @NotNull(message = "level is really necessary")
    private Integer code;//level
}
