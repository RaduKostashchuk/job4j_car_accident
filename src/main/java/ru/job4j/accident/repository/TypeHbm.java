package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Repository
public class TypeHbm {
    private final SessionFactory sf;

    public TypeHbm(SessionFactory sf) {
        this.sf = sf;
    }

    public List<AccidentType> getAll() {
        return Wrapper.tx(sf, session -> session.createQuery("select a from AccidentType a", AccidentType.class)
                .getResultList());
    }
}
