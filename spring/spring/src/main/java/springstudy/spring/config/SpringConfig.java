package springstudy.spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springstudy.spring.repository.JdbcTemplateMemberRepository;
import springstudy.spring.repository.MemberRepository;
import springstudy.spring.repository.MemoryMemberRepository;
import springstudy.spring.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());

    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
