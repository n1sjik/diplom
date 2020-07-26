package ru.spb.cmt.doctorcloud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
public class People extends BaseNamedEntity implements UserDetails {

    private String surname;
    private String name;
    private String patronymic;
    private String nameFull;
    private String cellular;
    private String pwd;
    private LocalDate birthdate;
    private Integer sex;
    private int status;
    @OneToMany(mappedBy = "people", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnoreProperties("people")
    private List<PeopleSection> peopleSectionList;
    @OneToMany(mappedBy = "people")
    @JsonIgnoreProperties("people")
    private List<PeopleVisit> visits;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    public String getCellular() {
        return cellular;
    }

    public void setCellular(String cellular) {
        this.cellular = cellular;
    }

    @JsonIgnore
    public String getPwd() {
        return pwd;
    }

    @JsonSetter
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PeopleSection> getPeopleSectionList() {
        return peopleSectionList;
    }

    public void setPeopleSectionList(List<PeopleSection> peopleSectionList) {
        this.peopleSectionList = peopleSectionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return cellular;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<PeopleVisit> getVisits() {
        return visits;
    }

    public void setVisits(List<PeopleVisit> visits) {
        this.visits = visits;
    }
}
