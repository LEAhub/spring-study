package springstudy.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springstudy.spring.domain.Member;
import springstudy.spring.service.MemberService;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    //기본 localhost를 main.html로 설정
    @GetMapping("/")
    public String checkLogin(){
        return "main";
    }

    //join.html로 연결하여 joinForm 작성
    @GetMapping("join")
    public String joinForm(){
        return "join";
    }

    //한 번 객체를 보내보았음 json 방식으로 출력됨
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


    Boolean checkPW = true;
    Boolean checkID = true;

    //아래에서 만든 비밀번호 일치 메소드를 활용함
    @PostMapping("result")
    public String successSignUp
                (MemberForm memberForm, @RequestParam("userPW") String userPW, @RequestParam("rePW") String rePW,
                 @RequestParam("userID") String id)
    {
        checkPW(userPW, rePW);
        checkID(id);
        if(checkPW&&checkID) {
            Member member = new Member();
            member.setName(memberForm.getName());
            member.setUserID(memberForm.getUserID());
            member.setUserPW(memberForm.getUserPW());
            memberService.join(member);

            return "redirect:/";
        }else if(checkID){
            return "fail_checkPW";
        }else if(checkPW){
            return "fail_checkID";
        }else
            return "join";

    }
    //입력 받은 두 비밀번호가 같은지 확인하는 메소드
    public Boolean checkPW(@RequestParam("userPW") String userPW, @RequestParam("rePW") String rePW){
        if(userPW.equals(rePW)) checkPW=true;
        else checkPW=false;
        return checkPW;
    }

    //입력 받은 id 값을 매개변수로 설정
    //memberService 참조변수의 findMembers()메소드를 활용하여 m이 가지고 있는 id와 입력한 id가 같을 경우
    //checkID는 false로 재할당
    public Boolean checkID(@RequestParam("userID") String id){
        checkID = true;
        Optional<Member> member = memberService.findMembers().stream().filter(m -> m.getUserID().equals(id)).findAny();
        member.ifPresent(m->checkID=false);
        return checkID;
    }

    //가입한 회원들을 모아와 List 객체가 참조하게 설정
    //Model 객체에 데이터를 담아 return에 해당하는 html에 보냄
    @GetMapping("members")
    public String showMembers(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members";
    }

    //로그인 버튼을 누를 때 연결되는 주소
    @GetMapping("login")
    public String loginForm(){
        return "login"; //login.html을 찾아감
    }

    //로그인 할 때 입력 받은 값은 MemberForm 객체에 담김
    @PostMapping("loginform")
    public String login(Member member, MemberForm form){
        member.setUserID(form.getUserID());
        member.setUserPW(form.getUserPW());
        memberService.login(member);
        return "successLogin";
    }


}
