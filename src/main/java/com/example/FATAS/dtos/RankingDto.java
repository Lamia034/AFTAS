package com.example.FATAS.dtos;

import com.example.FATAS.embeddable.RankingId;
import com.example.FATAS.entities.Competition;
import com.example.FATAS.entities.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RankingDto {
    private RankingId id;

    private Integer rank = 0;

    private Integer score = 0;
    @NotBlank(message = "Competition code is necessary.")
    @Pattern(regexp = "^[a-z]{3}-\\d{2}-\\d{2}-\\d{2}$", message = "Invalid competition code format")
    private String code;
    @NotBlank(message = "Member number is necessary.")
    private Integer num;
}
