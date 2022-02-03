package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TypeMem {
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();
    private final AtomicInteger typeId  = new AtomicInteger();

    public TypeMem() {
        types.put(1, AccidentType.of(1, "Превышение скорости"));
        types.put(2, AccidentType.of(2, "Нарушение правил парковки"));
        types.put(3, AccidentType.of(3, "Движение на запрещающий сигнал светофора"));
        types.put(4, AccidentType.of(4, "ДТП"));
}

    public AccidentType add(AccidentType type) {
        type.setId(typeId.incrementAndGet());
        types.put(type.getId(), type);
        return type;
    }

    public AccidentType findById(int id) {
        return types.get(id);
    }

    public List<AccidentType> getAll() {
        return types.values().stream().toList();
    }
}
