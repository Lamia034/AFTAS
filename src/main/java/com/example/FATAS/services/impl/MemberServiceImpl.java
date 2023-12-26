package com.example.FATAS.services.impl;

import com.example.FATAS.dtos.MemberDto;
import com.example.FATAS.dtos.CompetitionResponseDto;
import com.example.FATAS.dtos.MemberResponseDto;
import com.example.FATAS.entities.Competition;
import com.example.FATAS.entities.Member;
import com.example.FATAS.exceptions.ResourceNotFoundException;
import com.example.FATAS.repositories.CompetitionRepository;
import com.example.FATAS.repositories.MemberRepository;
import com.example.FATAS.services.MemberService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public MemberResponseDto saveMember(@Valid MemberDto memberDto) {
        try {
            Member member = modelMapper.map(memberDto, Member.class);
            Member savedMember = memberRepository.save(member);
            return modelMapper.map(savedMember, MemberResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save member: " + e.getMessage());
        }
    }




    @Override
    @Transactional
    public List<MemberResponseDto> getAllMembers(Pageable pageable) {
        try {
            Page<Member> memberPage = memberRepository.findAll(pageable);
            return memberPage.stream()
                    .map(member -> modelMapper.map(member, MemberResponseDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve members: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public MemberResponseDto getMemberById(Integer memberId) {
        try {
            Optional<Member> memberOptional = memberRepository.findById(memberId);
            if (memberOptional.isPresent()) {
                return modelMapper.map(memberOptional.get(), MemberResponseDto.class);
            } else {
                throw new ResourceNotFoundException("Member not found with ID: " + memberId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve member: " + e.getMessage());
        }
    }


@Override
@Transactional
public List<MemberResponseDto> getMembersByPartialName(String partialName) {
    List<Member> members = memberRepository.findByNameContaining(partialName);
    return members.stream()
            .map(member -> modelMapper.map(member, MemberResponseDto.class))
            .collect(Collectors.toList());
}

    @Override
    @Transactional
    public List<MemberResponseDto> getMembersByPartialFamilyName(String partialFamilyName) {
        List<Member> members = memberRepository.findByFamilyNameContaining(partialFamilyName);
        return members.stream()
                .map(member -> modelMapper.map(member, MemberResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteMemberById(Integer memberId) {
        try {
            Optional<Member> memberOptional = memberRepository.findById(memberId);
            if (memberOptional.isPresent()) {
                memberRepository.delete(memberOptional.get());
                return true;
            } else {
                return false;
            }
        }catch (Exception e) {
            throw new RuntimeException("Failed to delete members: " + e.getMessage());
        }
    }
    @Override
    @Transactional
    public MemberResponseDto updateMember(Integer memberId, MemberDto updatedMemberDto) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member existingMember = optionalMember.get();

            if (updatedMemberDto.getName() != null) {
                existingMember.setName(updatedMemberDto.getName());
            }
            if (updatedMemberDto.getFamilyName() != null) {
                existingMember.setFamilyName(updatedMemberDto.getFamilyName());
            }
            if (updatedMemberDto.getAccessionDate() != null) {
                existingMember.setAccessionDate(updatedMemberDto.getAccessionDate());
            }
            if (updatedMemberDto.getNationality() != null) {
                existingMember.setNationality(updatedMemberDto.getNationality());
            }
            if (updatedMemberDto.getIdentityDocument() != null) {
                existingMember.setIdentityDocument(updatedMemberDto.getIdentityDocument());
            }
            if (updatedMemberDto.getIdentityNumber() != null) {
                existingMember.setIdentityNumber(updatedMemberDto.getIdentityNumber());
            }
            Member updatedMember = memberRepository.save(existingMember);

            return modelMapper.map(updatedMember, MemberResponseDto.class);
        } else {
            throw new ResourceNotFoundException("Member not found");
        }
    }
}
