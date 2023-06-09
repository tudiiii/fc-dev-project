package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MemberRepository {

    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Member save(Member member) {

        if (member.getId() == null) {
            return insert(member);
        }
        return update(member);
    }

    private Member insert(Member member) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
            .withTableName("Member")
            .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(member);

        log.info(" member : {}", member);
        long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return Member.builder().id(id).email(member.getEmail()).nickname(member.getNickname())
            .birthday(member.getBirthday())
            .createdAt(member.getCreatedAt()).build();
    }

    private Member update(Member member) {
        // TODO : implemented
        return member;
    }
}
