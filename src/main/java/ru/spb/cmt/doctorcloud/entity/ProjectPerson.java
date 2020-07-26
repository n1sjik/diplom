package ru.spb.cmt.doctorcloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class ProjectPerson extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonIgnoreProperties("projectPersonList")
    private Project project;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_role_id")
    private PersonRole personRole;
    private String description;
    @CreatedDate
    private Instant createdate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_person_role_id")
    private PersonRole createPersonRole;
    private boolean isMainRole;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public PersonRole getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRole personRole) {
        this.personRole = personRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Instant createdate) {
        this.createdate = createdate;
    }

    public PersonRole getCreatePersonRole() {
        return createPersonRole;
    }

    public void setCreatePersonRole(PersonRole createPersonRole) {
        this.createPersonRole = createPersonRole;
    }

    public boolean isMainRole() {
        return isMainRole;
    }

    public void setMainRole(boolean mainRole) {
        isMainRole = mainRole;
    }
}
