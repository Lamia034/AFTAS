package com.example.FATAS.repositories;

import com.example.FATAS.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Integer> {
 //   Optional<Member> findByName(String memberName);

    List<Member> findByNameContaining(String partialName);

    List<Member> findByFamilyNameContaining(String partialFamilyName);
}
