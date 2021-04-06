package com.surveyapp.model;

import javax.persistence.Column;

public class Faculty {
    @Column(name = "Fcode")
    private String facultyCode;

    @Column(name = "Fname")
    private String facultyName;

    public Faculty(String facultyCode, String facultyName) {
        this.facultyCode = facultyCode;
        this.facultyName = facultyName;
    }

    public Faculty() {
        this(null, null);
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
