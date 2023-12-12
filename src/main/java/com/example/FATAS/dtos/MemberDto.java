package com.example.FATAS.dtos;

import com.example.FATAS.enumeration.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDto {
    private Integer num;
    @NotBlank
    private String name;
    @NotBlank
    private String familyName;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate accessionDate;
    @NotBlank
    private String nationality;
    @NotBlank
    private IdentityDocumentType identityDocument;
    @NotBlank
    private String identityNumber;
}
