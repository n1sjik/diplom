package ru.spb.cmt.doctorcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import ru.spb.cmt.doctorcloud.entity.Project;
import ru.spb.cmt.doctorcloud.entity.ProjectPeople;
import ru.spb.cmt.doctorcloud.entity.ProjectPerson;
import ru.spb.cmt.doctorcloud.repository.ProjectRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    @Transactional(readOnly = true)
    public List<Project> getAllSections() {
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional
    public Project save(Project project) {
        if (project.getId() == 0) {
            return projectRepository.save(project);
        }
        Project projectToUpdate = projectRepository.findByIdWithProjectPeopleProjectPerson(project.getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        projectToUpdate.setDescription(project.getDescription());
        projectToUpdate.setName(project.getName());
        projectToUpdate.setStatus(project.getStatus());
        Set<ProjectPeople> projectPeopleListToUpdate = project.getProjectPeopleSet();
        projectPeopleListToUpdate.clear();
        projectPeopleListToUpdate.addAll(project.getProjectPeopleSet());
        Set<ProjectPerson> projectPersonListToUpdate = project.getProjectPersonSet();
        projectPersonListToUpdate.clear();
        projectPersonListToUpdate.addAll(project.getProjectPersonSet());
        return projectToUpdate;
    }

    @Transactional
    public void remove(long id) {
        projectRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Project getProject(long id) {
        return projectRepository.findByIdWithProjectPeopleProjectPerson(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
}
