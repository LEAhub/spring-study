package springstudy.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springstudy.spring.domain.Member;
import springstudy.spring.repository.MemoryMemberRepository;

//@Controller
public class PageController {

//    @GetMapping("main")
//    public String checkLogin(){
//        return "main";
//    }
//
//    @GetMapping("login")
//    public String loginForm(){
//        return "login";
//    }
//    @GetMapping("join")
//    public String joinForm(){
//        return "join";
//    }
//
//    @PostMapping("result")
//    @ResponseBody
//    public Member successSignUp(@RequestParam("name") String name,
//                                @RequestParam("id") String id, @RequestParam("pw") String pw, Model model){
//        Member member = new Member();
//        member.setName(name);
//        member.setUserID(id);
//        member.setUserPW(pw);
//
//        model.addAttribute("member", member);
//        return member;
//    }
}
