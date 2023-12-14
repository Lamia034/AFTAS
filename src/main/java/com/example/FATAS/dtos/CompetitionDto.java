package com.example.FATAS.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CompetitionDto {
    @NotBlank
    @Pattern(regexp = "^[a-z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "Invalid competition code format")
    private String code;
  //  @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
   // @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;
  //  @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;
    @NotBlank
    private Integer numberOfParticipants;
    @NotBlank
    private String location;
    @NotBlank
    private Double amount;
}
