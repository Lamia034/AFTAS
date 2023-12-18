package com.example.FATAS.dtos;

import com.example.FATAS.entities.Competition;
import com.example.FATAS.entities.Fish;
import com.example.FATAS.entities.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class HuntingDto {

    private Integer id;
    private Integer numberOfFish;
    @NotBlank
    @Pattern(regexp = "^[a-z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "Invalid competition code format")
    private String code;//competition
    private Integer num;//member
    private String name;//fish
}
