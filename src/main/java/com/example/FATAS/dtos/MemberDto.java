package com.example.FATAS.dtos;

import com.example.FATAS.enumeration.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDto {
    private Integer num;
    @NotBlank(message = "name must not be empty")
    private String name;
    @NotBlank(message = "family name must not be empty")
    private String familyName;
    //@NotBlank
    @FutureOrPresent(message = "must be today's date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate accessionDate;
    @NotBlank(message = "nationality must not be empty")
    private String nationality;
    @NotNull
    private IdentityDocumentType identityDocument;
    @NotBlank
    private String identityNumber;
}
