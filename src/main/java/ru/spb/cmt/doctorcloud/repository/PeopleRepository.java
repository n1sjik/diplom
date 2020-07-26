package ru.spb.cmt.doctorcloud.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import ru.spb.cmt.doctorcloud.entity.People;

import java.util.Optional;

public interface PeopleRepository extends BaseRepository<People> {

    @Query("SELECT p FROM People p " +
            "LEFT JOIN FETCH p.peopleSectionList psl " +
            "LEFT JOIN FETCH psl.section s " +
            "WHERE p.cellular = :CELLULAR")
    Optional<People> findByCellular(@Param("CELLULAR") String cellular);

    @Query("SELECT p FROM People p " +
            "LEFT JOIN FETCH p.peopleSectionList psl " +
            "WHERE p.id = :ID")
    Optional<People> findByIdWithPeopleSectionList(@Param("ID") long id);
}
