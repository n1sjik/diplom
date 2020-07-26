package ru.spb.cmt.doctorcloud.entity;

public class SectionDTO {
    private long id;
    private String nameFull;
    private String nameShort;
    private SectionType sectionType;
    private int paidPlace;
    private int freePlace;
    private GeoPlace clubGeoPlace;
    private GeoPlace organizationGeoPlace;
    private boolean isMySection;

    public SectionDTO(long id, String nameFull, String nameShort, SectionType sectionType, int paidPlace, int freePlace, GeoPlace clubGeoPlace, GeoPlace organizationGeoPlace, long isMySection) {
        this.id = id;
        this.nameFull = nameFull;
        this.nameShort = nameShort;
        this.sectionType = sectionType;
        this.paidPlace = paidPlace;
        this.freePlace = freePlace;
        this.clubGeoPlace = clubGeoPlace;
        this.organizationGeoPlace = organizationGeoPlace;
        this.isMySection = isMySection > 0;
    }

    public long getId() {
        return id;
    }

    public String getNameFull() {
        return nameFull;
    }

    public String getNameShort() {
        return nameShort;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public int getPaidPlace() {
        return paidPlace;
    }

    public int getFreePlace() {
        return freePlace;
    }

    public GeoPlace getClubGeoPlace() {
        return clubGeoPlace;
    }

    public GeoPlace getOrganizationGeoPlace() {
        return organizationGeoPlace;
    }

    public boolean isMySection() {
        return isMySection;
    }
}
