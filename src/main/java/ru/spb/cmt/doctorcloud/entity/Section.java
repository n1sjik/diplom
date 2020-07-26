package ru.spb.cmt.doctorcloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section extends BaseNamedEntity {

    private String nameFull;
    private String nameShort;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_type_id")
    private SectionType sectionType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_person_id")
    private Person mainPerson;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;
    private int paidPlace;
    private int freePlace;
    @OneToMany(mappedBy = "section", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnoreProperties("section")
    private List<PeopleSection> peopleSectionList;

    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    public Person getMainPerson() {
        return mainPerson;
    }

    public void setMainPerson(Person mainPerson) {
        this.mainPerson = mainPerson;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public int getPaidPlace() {
        return paidPlace;
    }

    public void setPaidPlace(int paidPlace) {
        this.paidPlace = paidPlace;
    }

    public int getFreePlace() {
        return freePlace;
    }

    public void setFreePlace(int freePlace) {
        this.freePlace = freePlace;
    }

    public List<PeopleSection> getPeopleSectionList() {
        return peopleSectionList;
    }

    public void setPeopleSectionList(List<PeopleSection> peopleSectionList) {
        this.peopleSectionList = peopleSectionList;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
