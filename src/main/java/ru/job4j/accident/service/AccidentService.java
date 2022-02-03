package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.RuleJdbcTemplate;
import ru.job4j.accident.repository.TypeJdbcTemplate;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentJdbcTemplate accidents;
    private final TypeJdbcTemplate types;
    private final RuleJdbcTemplate rules;

    public AccidentService(AccidentJdbcTemplate accidents,
                           TypeJdbcTemplate types,
                           RuleJdbcTemplate rules) {
        this.accidents = accidents;
        this.types = types;
        this.rules = rules;
    }

    public Accident add(Accident accident, String[] ruleIds) {
        accident = accidents.save(accident, ruleIds);
        return accident;
    }

    public void update(Accident accident) {
        accidents.update(accident);
    }

    public List<Accident> getAllAccidents() {
        return accidents.getAll();
    }

    public List<AccidentType> getAllTypes() {
        return types.getAll();
    }

    public List<Rule> getAllRules() {
        return rules.getAll();
    }

    public Accident findAccidentById(int id) {
        return accidents.findById(id);
    }
}
