package com.example.FATAS.repositories;

import com.example.FATAS.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
}
