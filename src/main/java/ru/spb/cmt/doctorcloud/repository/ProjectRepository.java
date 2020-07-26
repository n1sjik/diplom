package ru.spb.cmt.doctorcloud.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.spb.cmt.doctorcloud.entity.Project;

import java.util.Optional;

public interface ProjectRepository extends BaseRepository<Project> {

    @Query("SELECT p FROM Project p " +
            "LEFT JOIN FETCH p.projectPeopleSet pPeop " +
            "LEFT JOIN FETCH p.projectPersonSet pPers " +
            "WHERE p.id = :ID")
    Optional<Project> findByIdWithProjectPeopleProjectPerson(@Param("ID") long id);
}
