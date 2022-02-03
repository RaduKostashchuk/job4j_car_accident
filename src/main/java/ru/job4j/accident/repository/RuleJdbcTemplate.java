package ru.job4j.accident.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class RuleJdbcTemplate {
    private final JdbcTemplate jdbc;

    public RuleJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Rule findById(int id) {
        return jdbc.query("select name from rule where id = (?)", new BeanPropertyRowMapper<>(Rule.class), id)
                .stream().findAny().orElse(null);
    }

    public List<Rule> getAll() {
        return jdbc.query("select id, name from rule", new BeanPropertyRowMapper<>(Rule.class));
    }
}
