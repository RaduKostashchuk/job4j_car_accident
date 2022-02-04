package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class RuleHbm {
    private final SessionFactory sf;

    public RuleHbm(SessionFactory sf) {
        this.sf = sf;
    }

    public Rule findById(int id) {
        return Wrapper.tx(sf, session -> session.createQuery("select r from Rule r where r.id = :id", Rule.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    public List<Rule> getAll() {
        return Wrapper.tx(sf, session -> session.createQuery("select r from Rule r", Rule.class).getResultList());
    }
}
