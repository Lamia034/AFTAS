package com.example.FATAS.dtos;

import com.example.FATAS.entities.Hunting;
import com.example.FATAS.entities.Ranking;
import com.example.FATAS.enumeration.IdentityDocumentType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MemberResponseDto {
    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private String identityNumber;
    private List<RankingDto> rankings;
    private List<HuntingDto> huntings;
}
