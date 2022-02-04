package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Repository
public class AccidentHbm {
    private final SessionFactory sf;

    public AccidentHbm(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        Wrapper.tx(sf, session -> session.save(accident));
        return accident;
    }

    public void update(Accident accident) {
        Wrapper.tx(sf, session -> session.createQuery("update Accident a set a.name = :name where a.id = :id")
                .setParameter("name", accident.getName())
                .setParameter("id", accident.getId())
                .executeUpdate());
    }

    public Accident findById(int id) {
        return Wrapper.tx(sf, session -> session.createQuery("select a from Accident a where a.id = :id", Accident.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    public List<Accident> getAll() {
        return Wrapper.tx(sf, session -> session.createQuery("select distinct a from Accident a"
                        + " join fetch a.type join fetch a.rules", Accident.class)
                .getResultList());
    }
}
