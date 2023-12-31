package com.example.FATAS.controllers;

import com.example.FATAS.dtos.MemberDto;
import com.example.FATAS.dtos.MemberResponseDto;
import com.example.FATAS.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping
    public ResponseEntity<MemberResponseDto> saveMember(@Valid @RequestBody MemberDto memberDto) {
        MemberResponseDto savedMember = memberService.saveMember(memberDto);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }
    @GetMapping
    public List<MemberResponseDto> getMembers(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return memberService.getAllMembers(pageable);
    }
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Integer memberId) {
        MemberResponseDto member = memberService.getMemberById(memberId);
        return ResponseEntity.ok(member);
    }

//    @GetMapping("/byName/{memberName}")
//    public ResponseEntity<MemberResponseDto> getMemberByName(@PathVariable String memberName) {
//        MemberResponseDto member = memberService.getMemberByName(memberName);
//        return ResponseEntity.ok(member);
//    }

    @GetMapping("/byName")
    public List<MemberResponseDto> getMembersByPartialName(@RequestParam String partialName) {
        List<MemberResponseDto> members = memberService.getMembersByPartialName(partialName);
        return ResponseEntity.ok(members).getBody();
    }
    @GetMapping("/byFamilyName")
    public List<MemberResponseDto> getMembersByPartialFamilyName(@RequestParam String partialFamilyName) {
        List<MemberResponseDto> members = memberService.getMembersByPartialFamilyName(partialFamilyName);
        return ResponseEntity.ok(members).getBody();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable Integer memberId) {
        memberService.deleteMemberById(memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Member deleted successfully");
    }
    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> updateMember(
            @PathVariable Integer memberId,
            @RequestBody MemberDto updatedMemberDto) {

        MemberResponseDto updatedMember = memberService.updateMember(memberId, updatedMemberDto);

        if (updatedMember != null) {
            return ResponseEntity.ok(updatedMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
