package ru.job4j.accident.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Repository
public class TypeJdbcTemplate {
    private final JdbcTemplate jdbc;

    public TypeJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public AccidentType findById(int id) {
        return jdbc.query("select name from type where id = (?)",
                new BeanPropertyRowMapper<>(AccidentType.class), id)
                .stream().findAny().orElse(null);
    }

    public List<AccidentType> getAll() {
        return jdbc.query("select id, name from type", new BeanPropertyRowMapper<>(AccidentType.class));
    }
}
