package com.example.FATAS.services;

import com.example.FATAS.dtos.CompetitionDto;
import com.example.FATAS.dtos.CompetitionResponseDto;
import com.example.FATAS.dtos.MemberDto;
import com.example.FATAS.dtos.MemberResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    MemberResponseDto saveMember(MemberDto memberDto);
    MemberResponseDto getMemberById(Integer memberId);
   // MemberResponseDto getMemberByName(String memberName);
    List<MemberResponseDto> getAllMembers(Pageable pageable);
    boolean deleteMemberById(Integer memberId);
    MemberResponseDto updateMember(Integer memberId, MemberDto updatedMemberDto);

    List<MemberResponseDto> getMembersByPartialName(String partialName);
    List<MemberResponseDto> getMembersByPartialFamilyName(String partialFamilyName);
}
