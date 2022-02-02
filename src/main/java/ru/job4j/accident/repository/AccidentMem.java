package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> store = new ConcurrentHashMap<>();
    private final AtomicInteger id  = new AtomicInteger();

    public AccidentMem() {
        Accident accident1 = Accident.of("Инцидент №1", "Превышение скорости", "Приморская 23");
        Accident accident2 = Accident.of("Инцидент №2", "Парковка на газоне", "Есенина 45");
        this.add(accident1);
        this.add(accident2);
    }

    public Accident add(Accident accident) {
       accident.setId(id.incrementAndGet());
       store.put(id.get(), accident);
       return accident;
    }

    public void update(Accident accident) {
        store.put(accident.getId(), accident);
    }

    public void remove(Accident accident) {
        store.remove(accident.getId());
    }

    public List<Accident> getAllAccidents() {
        return store.values().stream().toList();
    }

    public int size() {
        return store.size();
    }
}
