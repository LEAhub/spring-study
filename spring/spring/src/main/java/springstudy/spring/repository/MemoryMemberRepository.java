package springstudy.spring.repository;

import org.springframework.stereotype.Repository;
import springstudy.spring.domain.Member;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{
    private Map<String, Member> users = new HashMap<String, Member>();



    @Override
    public Member save(Member member) {
        users.put(member.getName(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        return users.values().stream().filter(member -> member.getUserID().equals(id)).findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.ofNullable(users.get(name));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(users.values());
    }

    public void clearUsers(){
        users.clear();
    }
}
