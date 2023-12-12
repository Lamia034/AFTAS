package com.example.FATAS.entities;

import com.example.FATAS.enumeration.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity

public class Member {
    @Id
    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private String identityNumber;
//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Competition> competitions;
@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
private List<Ranking> rankings;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Hunting> huntings;
}
