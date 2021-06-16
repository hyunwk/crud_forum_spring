package com.victolee.board.controller;
import com.victolee.board.domain.Member;
import com.victolee.board.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;
    /*
    회원가입
     */
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPw(form.getPw());

        memberService.join(member);

        return "redirect:/";
    }
    /*
    로그인
    */
    @GetMapping("/members/login")
    public String loginForm() {
        return "members/loginMemberForm";
    }

    @PostMapping("/members/login")
    public String login(MemberForm form, Model model) {
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setPw(form.getPw());

        if (memberService.login(member)){
            //로그인 성공
            model.addAttribute("email", member.getEmail());
            return "redirect:/";
        }
        //로그인 실패
        return "/members/loginMemberForm";
//        return "/members/login";
    }

    /*
    멤버 조회
     */
    @GetMapping("/members")
    public String lst(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}