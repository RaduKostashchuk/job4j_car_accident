package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
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

    public List<AccidentType> getAllTypes() {
        return store.getAllTypes();
    }

    public Accident findAccidentById(int id) {
        return store.findAccidentById(id);
    }

    public AccidentType findTypeById(int id) {
        return store.findTypeById(id);
    }
}
