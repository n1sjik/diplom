package ru.spb.cmt.doctorcloud.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.spb.cmt.doctorcloud.entity.Club;

import java.util.Optional;

public interface ClubRepository extends BaseRepository<Club> {

    @Query("SELECT c FROM Club c " +
            "LEFT JOIN FETCH c.organization o " +
            "LEFT JOIN FETCH c.mainPerson mp " +
            "LEFT JOIN FETCH c.geoPlace gp " +
            "WHERE c.id = :ID")
    Optional<Club> findByIdWithOrganizationMainPersonGeoPlace(@Param("ID") long id);
}
