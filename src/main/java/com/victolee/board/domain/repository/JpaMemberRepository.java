package com.victolee.board.domain.repository;
import javax.persistence.EntityManager;
import com.victolee.board.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery(
                "select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    // 5/18
    public Optional<Member> findByEmail(String email) {
        List<Member> result = em.createQuery(
                "select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
        return result.stream().findAny();
    }

    // 5/18
    public Optional<Member> checkPw(String email, String pw) {
        List<Member> result = em.createQuery(
                "select m from Member m where m.email= :email and m.pw= :pw", Member.class)
                .setParameter("email", email)
                .setParameter("pw", pw)
                .getResultList();
        return result.stream().findAny();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
