package ru.spb.cmt.doctorcloud.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.spb.cmt.doctorcloud.entity.Section;
import ru.spb.cmt.doctorcloud.entity.SectionDTO;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends BaseRepository<Section> {

    @Query("SELECT s FROM Section s " +
            "LEFT JOIN FETCH s.sectionType st " +
            "LEFT JOIN FETCH s.mainPerson mp " +
            "LEFT JOIN FETCH s.club c " +
            "LEFT JOIN FETCH s.organization o " +
            "LEFT JOIN FETCH s.peopleSectionList psl " +
            "WHERE s.id = :ID")
    Optional<Section> findByIdWithSectionTypeMainPersonClubOrganizationPeopleSections(@Param("ID") long id);

    @Query("SELECT s," +
            "(SELECT COUNT(ps) FROM PeopleSection ps JOIN ps.section sec WHERE ps.people.id = :PEOPLEID AND sec.id = s.id)" +
            " FROM Section s " +
            "LEFT JOIN FETCH s.organization o " +
            "LEFT JOIN FETCH o.geoPlace ogp " +
            "LEFT JOIN FETCH s.club c " +
            "LEFT JOIN FETCH c.geoPlace cgp " +
            "LEFT JOIN FETCH s.sectionType st")
    List<Object[]> search(@Param("PEOPLEID") long peopleId);
}
