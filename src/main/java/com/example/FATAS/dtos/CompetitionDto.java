package com.example.FATAS.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CompetitionDto {
    @NotBlank
    @Pattern(regexp = "^[a-z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "Invalid competition code format")
    private String code;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotBlank
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;
    @NotBlank
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;
    @NotBlank
    private Integer numberOfParticipants;
    @NotBlank
    private String location;
    @NotBlank
    private Double amount;
}
