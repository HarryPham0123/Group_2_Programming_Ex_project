package com.surveyapp.model;

import javax.persistence.Column;

public class AcademicYearFaculty {
    @Column(name = "AYcode")
    private String AYcode;

    @Column(name = "Fcode")
    private String Fcode;

    public AcademicYearFaculty() {
        this.AYcode = "";
        this.Fcode = "";
    }

    public AcademicYearFaculty(String AYcode, String Fcode) {
        this.AYcode = AYcode;
        this.Fcode = Fcode;
    }

    public String getAYcode() {
        return AYcode;
    }

    public String getFcode() {
        return Fcode;
    }

    public void setAYcode(String AYcode) {
        this.AYcode = AYcode;
    }

    public void setFcode(String Fcode) {
        this.Fcode = Fcode;
    }

}
