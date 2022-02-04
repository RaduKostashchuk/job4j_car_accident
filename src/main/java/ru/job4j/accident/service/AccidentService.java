package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.*;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentHbm accidents;
    private final TypeHbm types;
    private final RuleHbm rules;

    public AccidentService(AccidentHbm accidents, TypeHbm types, RuleHbm rules) {
        this.accidents = accidents;
        this.types = types;
        this.rules = rules;
    }

    public Accident add(Accident accident, String[] ruleIds) {
        for (String ruleId : ruleIds) {
            accident.addRule(rules.findById(Integer.parseInt(ruleId)));
        }
        accident = accidents.save(accident);
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
