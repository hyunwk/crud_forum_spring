package com.victolee.board.service;
import com.victolee.board.domain.Member;
import com.victolee.board.domain.repository.JpaMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class MemberService {
    private final JpaMemberRepository memberRepository;

    /*
    회원가입
    */
    @Transactional
    public long join(Member member) {
        validateDuplicateMember(member);
        //validateFormatMember(member);
        memberRepository.save(member);
        return member.getId_member();
    }

    //같은 이메일이 있는 중복회원 x
    @Transactional
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //양식 검사
    @Transactional
    private void validateFormatMember(Member member){
        if (! (isValidEmail(member.getEmail())
                && isValidName(member.getName())
                && isValidPw(member.getPw())))
            throw new IllegalStateException("올바른 입력 형식이 아닙니다");
    }

    @Transactional
    public static boolean isValidEmail(String email)
    {
        boolean err = false;
        // 영문자숫자 2~10자 @ 영문자숫자 2~10자  . 알파벳 2~3자
        String regex = "^[a-zA-Z0-9-]{2,10}+@[a-zA-Z0-9-]{2,10}+.[a-zA-Z]{2,3}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }

    @Transactional
    public static boolean isValidName(String name)
    {
        boolean err = false;
        //한글 2~5
        String regex = "^[가-힣]{2,5}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        if(m.matches()) {
            err = true;
        }
        return err;
    }
    @Transactional
    public static boolean isValidPw(String pw)
    {
        boolean err = false;
        // 영문자,숫자 8~15
        String regex = "^[A-Za-z0-9]{8,15}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pw);
        if(m.matches()) {
            err = true;
        }
        return err;
    }
    @Transactional
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /*
    로그인
     */
    @Transactional
    public boolean login(Member member){
        return validateLoginMember(member).get().equals(member);
    }

    // 5.18 로그인 검증
    @Transactional
    private Optional<Member> validateLoginMember(Member member) {
//        memberRepository.checkPw(member.getEmail(), member.getPw())
//                .ifPresentOrElse(m -> System.out.println("로그인성공 :" +member.getEmail()),
//                        () -> {throw new IllegalStateException("회원 정보가 없습니다.");}
//                );
        return Optional.ofNullable(member);
    }
}