package ru.spb.cmt.doctorcloud.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.spb.cmt.doctorcloud.entity.Organization;

import java.util.Optional;

public interface OrganizationRepository extends BaseRepository<Organization> {

    @Query("SELECT o FROM Organization o " +
            "LEFT JOIN FETCH o.mainPerson p " +
            "LEFT JOIN FETCH o.geoPlace gp " +
            "WHERE o.id = :ID")
    Optional<Organization> findByIdWithGeoPlacePerson(@Param("ID") long id);
}
