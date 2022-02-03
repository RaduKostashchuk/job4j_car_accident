package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident, String[] ruleIds) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement("insert into accident (name, type_id) values ((?), (?))",
                    new String[] {"id"});
            ps.setString(1, accident.getName());
            ps.setInt(2, accident.getType().getId());
            return ps;
        }, holder);
        accident.setId(Objects.requireNonNull(holder.getKey()).intValue());
        for (String ruleId : ruleIds) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) values ((?), (?))",
                    accident.getId(), Integer.parseInt(ruleId));
        }
        return accident;
    }

    public void update(Accident accident) {
        jdbc.update("update accident set name = (?) where id = (?)",
                accident.getName(), accident.getId());
    }

    public Accident findById(int id) {
        return jdbc.query("select a.name accName, b.id typeId, b.name typeName, d.id ruleId, d.name ruleName"
                          +  " from accident a join type b on a.type_id = b.id "
                          +  "join accident_rule c on a.id = c.accident_id join rule d on c.rule_id = d.id"
                          +  " where a.id = (?)",
                rs -> {
                    rs.next();
                    Accident accident = new Accident();
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("typeId"));
                    type.setName(rs.getString("typeName"));
                    accident.setName(rs.getString("accName"));
                    accident.setId(id);
                    accident.setType(type);
                    do {
                        Rule rule = new Rule();
                        rule.setId(rs.getInt("ruleId"));
                        rule.setName(rs.getString("ruleName"));
                        accident.addRule(rule);
                    } while (rs.next());
                    return accident;
                },
                id);
    }

    public List<Accident> getAll() {
        return jdbc.query("select a.id accId, a.name accName, b.id typeId, b.name typeName, d.id ruleId, d.name ruleName"
                        +  " from accident a join type b on a.type_id = b.id "
                        +  "join accident_rule c on a.id = c.accident_id join rule d on c.rule_id = d.id",
                (rs) -> {
                    Map<Integer, Accident> map = new HashMap<>();
                    while (rs.next()) {
                        map.computeIfPresent(rs.getInt("accId"), (id, accident) -> {
                            Rule rule = new Rule();
                            try {
                                rule.setId(rs.getInt("ruleId"));
                                rule.setName(rs.getString("ruleName"));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            accident.addRule(rule);
                            return accident;
                        });
                        map.computeIfAbsent(rs.getInt("accId"), id -> {
                            Accident accident = new Accident();
                            AccidentType type = new AccidentType();
                            Rule rule = new Rule();
                            try {
                                accident.setId(rs.getInt("accId"));
                                accident.setName(rs.getString("accName"));
                                type.setId(rs.getInt("typeId"));
                                type.setName(rs.getString("typeName"));
                                rule.setId(rs.getInt("ruleId"));
                                rule.setName(rs.getString("ruleName"));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            accident.setType(type);
                            accident.addRule(rule);
                            return accident;
                        });
                    }
                    return map.values().stream().toList();
                });
    }
}
