package ru.spb.cmt.doctorcloud.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dict_section_type")
public class SectionType extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
