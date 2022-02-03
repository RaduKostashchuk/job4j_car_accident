package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger accidentId  = new AtomicInteger();

    public AccidentMem(TypeMem types, RuleMem rules) {
        Accident accident1 = Accident.of("Инцидент №1", "Превышение скорости", "Приморская 23", types.findById(1));
        accident1.addRule(rules.findById(1));
        accident1.addRule(rules.findById(2));
        Accident accident2 = Accident.of("Инцидент №2", "Парковка на газоне", "Есенина 45", types.findById(2));
        accident2.addRule(rules.findById(2));
        accident2.addRule(rules.findById(3));
        this.add(accident1);
        this.add(accident2);
    }

    public Accident add(Accident accident) {
       accident.setId(accidentId.incrementAndGet());
       accidents.put(accidentId.get(), accident);
       return accident;
    }

    public void remove(Accident accident) {
        accidents.remove(accident.getId());
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public List<Accident> getAll() {
        return accidents.values().stream().toList();
    }
}
