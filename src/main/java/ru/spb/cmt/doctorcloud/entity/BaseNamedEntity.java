package ru.spb.cmt.doctorcloud.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseNamedEntity extends BaseEntity {

    public abstract String getNameFull();
}
