package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("select distinct a from Accident a join fetch a.type join fetch a.rules")
    List<Accident> findAll();

    @Query("select a from Accident a join fetch a.type join fetch a.rules where a.id = :id")
    Optional<Accident> findById(@Param("id") int id);
}
