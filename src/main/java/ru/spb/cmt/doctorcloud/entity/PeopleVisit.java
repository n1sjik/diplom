package ru.spb.cmt.doctorcloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class PeopleVisit extends BaseEntity {

    @CreatedDate
    private Instant createdate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    @JsonIgnoreProperties("visits")
    private People people;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;
    @Column(name = "visit_dat")
    private LocalDateTime visitDate;
    private int commitByPeople;
    private int commitByPerson;

    public PeopleVisit() {
    }

    public PeopleVisit(People people, Section section) {
        this.people = people;
        this.section = section;
        this.visitDate = LocalDateTime.now();
        this.commitByPeople = 1;
    }

    public Instant getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Instant createdate) {
        this.createdate = createdate;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public int getCommitByPeople() {
        return commitByPeople;
    }

    public void setCommitByPeople(int commitByPeople) {
        this.commitByPeople = commitByPeople;
    }

    public int getCommitByPerson() {
        return commitByPerson;
    }

    public void setCommitByPerson(int commitByPerson) {
        this.commitByPerson = commitByPerson;
    }
}
