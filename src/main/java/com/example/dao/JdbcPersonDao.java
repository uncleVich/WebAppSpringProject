package com.example.dao;

import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcPersonDao implements PersonDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("select id, first_name, last_name from t_person",
                new RowMapper<Person>() {
                    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Person person = new Person();
                        person.setId(rs.getLong("id"));
                        person.setFirstName(rs.getString("first_name"));
                        person.setLastName(rs.getString("last_name"));

                        return person;
                    }
                });
    }

    public Person findById(final Long id) {
        return jdbcTemplate.queryForObject("select first_name, last_name from t_person where id = ?",
                new RowMapper<Person>() {
                    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Person person = new Person();
                        person.setId(id);
                        person.setFirstName(rs.getString("first_name"));
                        person.setLastName(rs.getString("last_name"));

                        return person;
                    }
                }, id);
    }

    public void create(final Person person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt = con.prepareStatement("insert into t_person(id, first_name, last_name) " +
                        "values (t_person_sequence.nextval, ?,?)");
                stmt.setString(1, person.getFirstName());
                stmt.setString(2, person.getLastName());

                return stmt;
            }
        }, keyHolder);
        person.setId(keyHolder.getKey().longValue());
    }

    public void update(Person person) {
        jdbcTemplate.update("update t_person set first_name = ?, last_name = ? where id = ?",
                person.getFirstName(), person.getLastName(), person.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update("delete from t_person where id = ?", id);

    }
}
