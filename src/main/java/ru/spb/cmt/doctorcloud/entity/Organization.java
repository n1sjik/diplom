package ru.spb.cmt.doctorcloud.entity;

import javax.persistence.*;

@Entity
public class Organization extends BaseNamedEntity {

    private String nameFull;
    private String nameShort;
    private String city;
    private String cityArea;
    private String fullAddress;
    private String contactPhone;
    private String contactEmail;
    private String site;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_person_id")
    private Person mainPerson;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "geo_place_id")
    private GeoPlace geoPlace;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityArea() {
        return cityArea;
    }

    public void setCityArea(String cityArea) {
        this.cityArea = cityArea;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Person getMainPerson() {
        return mainPerson;
    }

    public void setMainPerson(Person mainPerson) {
        this.mainPerson = mainPerson;
    }

    public GeoPlace getGeoPlace() {
        return geoPlace;
    }

    public void setGeoPlace(GeoPlace geoPlace) {
        this.geoPlace = geoPlace;
    }
}
