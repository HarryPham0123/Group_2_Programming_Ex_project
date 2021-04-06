package com.surveyapp.model;

import javax.persistence.Column;

public class AcademicYear {
    @Column(name = "AYcode")
    private String code;

    public AcademicYear(String code) {
        this.code = code;
    }

    public AcademicYear() {
        this(null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

