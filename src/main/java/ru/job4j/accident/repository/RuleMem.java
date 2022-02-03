package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final AtomicInteger ruleId  = new AtomicInteger();

    public RuleMem() {
        rules.put(1, Rule.of(1, "Статья №1"));
        rules.put(2, Rule.of(2, "Статья №2"));
        rules.put(3, Rule.of(3, "Статья №3"));
    }

    public Rule add(Rule rule) {
        rule.setId(ruleId.incrementAndGet());
        rules.put(rule.getId(), rule);
        return rule;
    }

    public Rule findById(int id) {
        return rules.get(id);
    }

    public List<Rule> getAll() {
        return rules.values().stream().toList();
    }
}
