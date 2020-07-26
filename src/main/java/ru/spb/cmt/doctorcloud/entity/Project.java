package ru.spb.cmt.doctorcloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Entity
public class Project extends BaseNamedEntity {

    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_person_role_id")
    private PersonRole createPersonRole;
    @CreatedDate
    private Instant createdate;
    private int status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_type_id")
    private ProjectType projectType;
    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties("project")
    private List<ProjectRate> rates;
    @OneToMany(mappedBy = "project", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnoreProperties("project")
    private Set<ProjectPeople> projectPeopleSet;
    @OneToMany(mappedBy = "project", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnoreProperties("project")
    private Set<ProjectPerson> projectPersonSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PersonRole getCreatePersonRole() {
        return createPersonRole;
    }

    public void setCreatePersonRole(PersonRole createPersonRole) {
        this.createPersonRole = createPersonRole;
    }

    public Instant getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Instant createdate) {
        this.createdate = createdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public List<ProjectRate> getRates() {
        return rates;
    }

    public void setRates(List<ProjectRate> rates) {
        this.rates = rates;
    }

    public Set<ProjectPeople> getProjectPeopleSet() {
        return projectPeopleSet;
    }

    public void setProjectPeopleSet(Set<ProjectPeople> projectPeopleSet) {
        this.projectPeopleSet = projectPeopleSet;
    }

    public Set<ProjectPerson> getProjectPersonSet() {
        return projectPersonSet;
    }

    public void setProjectPersonSet(Set<ProjectPerson> projectPersonSet) {
        this.projectPersonSet = projectPersonSet;
    }

    @Override
    @JsonIgnore
    public String getNameFull() {
        return name;
    }
}
