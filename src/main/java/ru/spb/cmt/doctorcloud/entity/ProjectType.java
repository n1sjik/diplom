package ru.spb.cmt.doctorcloud.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dict_project_type")
public class ProjectType extends BaseEntity {
    private long rootId;
    private String name;
    private int status;

    public long getRootId() {
        return rootId;
    }

    public void setRootId(long rootId) {
        this.rootId = rootId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
