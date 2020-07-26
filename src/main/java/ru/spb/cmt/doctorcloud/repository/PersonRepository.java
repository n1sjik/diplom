package ru.spb.cmt.doctorcloud.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.spb.cmt.doctorcloud.entity.Person;

import java.util.Optional;

public interface PersonRepository extends BaseRepository<Person> {

    Optional<Person> findByCellular(String s);


    @Query("SELECT p FROM Person p " +
            "LEFT JOIN FETCH p.roles r " +
            "WHERE p.id = :ID")
    Optional<Person> findByIdWithRoles(@Param("ID") long id);
}
