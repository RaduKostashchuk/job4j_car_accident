package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> store = new ConcurrentHashMap<>();

    public synchronized Accident add(Accident accident) {
       int id = store.keySet().stream().mapToInt(i -> i).max().orElse(1);
       id++;
       accident.setId(id);
       store.put(id, accident);
       return accident;
    }

    public synchronized void update(Accident accident) {
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
