package com.mkolisnyk.tracker.core.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer idValue) {
        this.id = idValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameValue) {
        this.name = nameValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String codeValue) {
        this.code = codeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project project)) {
            return false;
        }
        return Objects.equals(getId(), project.getId())
                && Objects.equals(getName(), project.getName()) && Objects.equals(getCode(), project.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCode());
    }
}
