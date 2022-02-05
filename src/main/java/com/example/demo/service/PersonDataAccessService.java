package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jsdbTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jsdbTemplate) {
        this.jsdbTemplate = jsdbTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "select id,name from person where id = ?";
        Person person = jsdbTemplate.queryForObject(sql, (rs, i) -> {
            UUID personId = UUID.fromString(rs.getString("id"));
            String name = rs.getString("name");
            return new Person(personId, name);
        }, new Object[]{id});
        return Optional.ofNullable(person);
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "select id,name from person";
        return jsdbTemplate.query(sql, (rs, i) -> {
            UUID id = UUID.fromString(rs.getString("id"));
            String name = rs.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }

}
