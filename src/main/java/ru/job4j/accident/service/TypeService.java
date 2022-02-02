package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class TypeService {
    private final AccidentMem store;

    public TypeService(AccidentMem store) {
        this.store = store;
    }

    public List<AccidentType> getAll() {
        return store.getAllTypes();
    }

    public AccidentType findById(int id) {
        return store.findTypeById(id);
    }
}
