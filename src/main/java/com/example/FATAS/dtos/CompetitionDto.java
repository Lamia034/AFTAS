package com.example.FATAS.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CompetitionDto {
    @NotBlank
    @Pattern(regexp = "^[a-z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "Invalid competition code format")
    private String code;
  //  @NotNull
    @FutureOrPresent(message = "not valid date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
   // @NotNull
   // @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
  //  @NotNull
 //   @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @Min(value = 1, message = "Number of participants must be at least 1")
    private Integer numberOfParticipants;

    private String location;
    @Min(value = 0, message = "Amount must be at least 0 (free)")
    private Double amount;
}
