package springstudy.spring.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import springstudy.spring.domain.Member;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.lang.String;

public class JdbcTemplateMemberRepository implements MemberRepository{


    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("member").usingColumns("name", "userID", "userPW");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        parameters.put("userID", member.getUserID());
        parameters.put("userPW", member.getUserPW());
        jdbcInsert.execute(parameters);

        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        List<Member> result = jdbcTemplate.query("select * from member where userID = ?",
                memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?",
                memberRowMapper(), name);
        return result.stream().findAny();
    }



    @Override
    public List<Member> findAll() {

        return jdbcTemplate.query("select * from member", memberRowMapper());

    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setName(rs.getString("name"));
            member.setUserID(rs.getString("userID"));
            member.setUserPW(rs.getString("userPW"));
            return member;
        };
    }
}
