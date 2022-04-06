package springstudy.spring.repository;


import springstudy.spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //회원가입
    public Member save(Member member);
    //아이디로 회원 찾기
    public Optional<Member> findById(String id);
    //이름으로 회원 찾기
    public Optional<Member> findByName(String name);
    //모든 회원 정보 보기
    public List<Member> findAll();
}
