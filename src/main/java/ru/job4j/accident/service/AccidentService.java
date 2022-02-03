package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.RuleMem;
import ru.job4j.accident.repository.TypeMem;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentMem accidents;
    private final TypeMem types;
    private final RuleMem rules;

    public AccidentService(AccidentMem accidents, TypeMem types, RuleMem rules) {
        this.accidents = accidents;
        this.types = types;
        this.rules = rules;
    }

    public Accident add(Accident accident, String[] ruleIds) {
        AccidentType type = types.findById(accident.getType().getId());
        accident.setType(type);
        for (String el : ruleIds) {
            Rule rule = rules.findById(Integer.parseInt(el));
            accident.addRule(rule);
        }
        return accidents.add(accident);
    }

    public AccidentType add(AccidentType type) {
        return types.add(type);
    }

    public Rule add(Rule rule) {
        return rules.add(rule);
    }

    public void update(Accident accident) {
        Accident original = accidents.findById(accident.getId());
        original.setName(accident.getName());
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
