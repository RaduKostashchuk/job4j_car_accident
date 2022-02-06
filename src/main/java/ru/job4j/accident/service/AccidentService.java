package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.*;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentRepository accidents;
    private final TypeRepository types;
    private final RuleRepository rules;

    public AccidentService(AccidentRepository accidents, TypeRepository types, RuleRepository rules) {
        this.accidents = accidents;
        this.types = types;
        this.rules = rules;
    }

    public Accident save(Accident accident, String[] ruleIds) {
        for (String ruleId : ruleIds) {
            Rule rule = rules.findById(Integer.parseInt(ruleId)).orElse(null);
            if (rule != null) {
                accident.addRule(rule);
            }
        }
        accident = accidents.save(accident);
        return accident;
    }

    public void update(Accident accident) {
        Accident original = accidents.findById(accident.getId()).get();
        original.setName(accident.getName());
        accidents.save(original);
    }

    public List<Accident> getAllAccidents() {
        return accidents.findAll();
    }

    public List<AccidentType> getAllTypes() {
        return (List<AccidentType>) types.findAll();
    }

    public List<Rule> getAllRules() {
        return (List<Rule>) rules.findAll();
    }

    public Accident findAccidentById(int id) {
        return accidents.findById(id).get();
    }
}
