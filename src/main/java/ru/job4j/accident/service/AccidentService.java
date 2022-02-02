package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {
    private final AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public Accident add(Accident accident) {
        return store.add(accident);
    }

    public void update(Accident accident) {
        store.update(accident);
    }

    public List<Accident> getAllAccidents() {
        return store.getAllAccidents();
    }

    public Accident findById(int id) {
        return store.findById(id);
    }
}
