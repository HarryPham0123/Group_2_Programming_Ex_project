package com.surveyapp.model;

import javax.persistence.Column;

public class Faculty {
    @Column(name = "Fcode")
    private String code;

    @Column(name = "Fname")
    private String name;

    public Faculty(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Faculty() {
        this(null, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
