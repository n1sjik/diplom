package ru.spb.cmt.doctorcloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.time.LocalDate;

@Entity
public class PeopleSection extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    @JsonIgnoreProperties("sections")
    private People people;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    @JsonIgnoreProperties("people")
    private Section section;
    private int status;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    @CreatedDate
    private Instant createdate;
    private int isFee;

    public PeopleSection() {
    }

    public PeopleSection(People people, Section section, int status, LocalDate dateFrom, LocalDate dateTo, int isFee) {
        this.people = people;
        this.section = section;
        this.status = status;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.isFee = isFee;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Instant getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Instant createdate) {
        this.createdate = createdate;
    }

    public int getIsFee() {
        return isFee;
    }

    public void setIsFee(int isFee) {
        this.isFee = isFee;
    }
}
