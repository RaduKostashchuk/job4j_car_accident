package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();
    private final AtomicInteger accidentId  = new AtomicInteger();
    private final AtomicInteger typeId  = new AtomicInteger();

    public AccidentMem() {
        types.put(1, AccidentType.of(1, "Превышение скорости"));
        types.put(2, AccidentType.of(2, "Нарушение правил парковки"));
        types.put(3, AccidentType.of(3, "Движение на запрещающий сигнал светофора"));
        types.put(4, AccidentType.of(4, "ДТП"));
        Accident accident1 = Accident.of("Инцидент №1", "Превышение скорости", "Приморская 23", types.get(1));
        Accident accident2 = Accident.of("Инцидент №2", "Парковка на газоне", "Есенина 45", types.get(2));
        this.add(accident1);
        this.add(accident2);
    }

    public Accident add(Accident accident) {
       accident.setId(accidentId.incrementAndGet());
       accidents.put(accidentId.get(), accident);
       return accident;
    }

    public AccidentType add(AccidentType type) {
        type.setId(typeId.incrementAndGet());
        types.put(type.getId(), type);
        return type;
    }

    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public void remove(Accident accident) {
        accidents.remove(accident.getId());
    }

    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    public List<Accident> getAllAccidents() {
        return accidents.values().stream().toList();
    }

    public List<AccidentType> getAllTypes() {
        return types.values().stream().toList();
    }
}
