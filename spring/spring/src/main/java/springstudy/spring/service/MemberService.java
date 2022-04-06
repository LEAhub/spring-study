package springstudy.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springstudy.spring.domain.Member;
import springstudy.spring.exception.CustomException;
import springstudy.spring.repository.MemberRepository;
import springstudy.spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository repository;


    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public String join(Member member){
        checkSameID(member);
        repository.save(member);
        return member.getName();
    }

    private void checkSameID(Member member){
        repository.findById(member.getUserID()).
                ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers(){
        return repository.findAll();
    }

    public String login(Member member){
        checkSamePW(member);
        return repository.findById(member.getUserID()).get().getUserID();
    }

    public void checkSamePW(Member member){
        repository.findById(member.getUserID()).
            ifPresent(m->
            {
                if (!m.getUserPW().equals(member.getUserPW())) throw new IllegalStateException("nonono");
            });
    }
}
