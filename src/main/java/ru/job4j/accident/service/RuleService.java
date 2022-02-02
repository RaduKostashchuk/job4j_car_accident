package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class RuleService {
    private final AccidentMem store;

    public RuleService(AccidentMem store) {
        this.store = store;
    }

    public List<Rule> getAll() {
        return store.getAllRules();
    }

    public Rule findById(int id) {
        return store.findRuleById(id);
    }
}
