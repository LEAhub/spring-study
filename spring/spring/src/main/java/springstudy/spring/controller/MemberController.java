package springstudy.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springstudy.spring.domain.Member;
import springstudy.spring.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String checkLogin(){
        return "main";
    }

    @GetMapping("login")
    public String loginForm(){
        return "login";
    }
    @GetMapping("join")
    public String joinForm(){
        return "join";
    }

//    @PostMapping("result")
//    @ResponseBody
//    public Member successSignUp(MemberForm memberForm){
//        Member member = new Member();
//        member.setName(memberForm.getName());
//        member.setUserID(memberForm.getUserID());
//        member.setUserPW(memberForm.getUserPW());
//        memberService.join(member);
//
//        return member;
//    }



    @PostMapping("result")
    public String successSignUp(MemberForm memberForm){
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setUserID(memberForm.getUserID());
        member.setUserPW(memberForm.getUserPW());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("members")
    public String showMembers(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members";
    }

    @PostMapping("login")
    public String login(Member member, MemberForm form){
        member.setUserID(form.getUserID());
        member.setUserPW(form.getUserPW());
        memberService.login(member);
        return "successLogin";
    }
}
