package com.example.FATAS.services;

import com.example.FATAS.dtos.CompetitionDto;
import com.example.FATAS.dtos.CompetitionResponseDto;
import com.example.FATAS.dtos.MemberDto;
import com.example.FATAS.dtos.MemberResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    MemberResponseDto saveMember(MemberDto memberDto);

    List<MemberResponseDto> getAllMembers(Pageable pageable);
    boolean deleteMemberById(Integer memberId);
    MemberResponseDto updateMember(Integer memberId, MemberDto updatedMemberDto);
}
