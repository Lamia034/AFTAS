package com.example.FATAS.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LevelDto {
    private Integer code;
    @NotBlank(message = "description is necessary")
    private String description;
    @NotNull(message = "points are necessary")
    private Integer points;
}
