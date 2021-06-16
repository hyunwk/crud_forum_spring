package com.victolee.board.domain.repository;

import com.victolee.board.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id_member);
    Optional<Member> findByName(String name);
    List<Member>  findAll();
    Optional<Member> checkPw(String email, String pw);
}
