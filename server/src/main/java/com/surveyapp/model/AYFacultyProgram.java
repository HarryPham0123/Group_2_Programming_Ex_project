package com.surveyapp.model;

import javax.persistence.Column;

public class AYFacultyProgram {
    @Column(name = "AYcode")
    private String AYcode;

    @Column(name = "Fcode")
    private String Fcode;

    @Column(name = "Pcode")
    private String Pcode;

    public AYFacultyProgram() {
        this.AYcode = "";
        this.Fcode = "";
        this.Pcode = "";
    }

    public AYFacultyProgram(String AYcode, String Fcode, String Pcode) {
        this.AYcode = AYcode;
        this.Fcode = Fcode;
        this.Pcode = Pcode;
    }

    public String getAYcode() {
        return AYcode;
    }

    public String getFcode() {
        return Fcode;
    }

    public String getPcode() {
        return Pcode;
    }

    public void setAYcode(String AYcode) {
        this.AYcode = AYcode;
    }

    public void setFcode(String Fcode) {
        this.Fcode = Fcode;
    }

    public void setPcode(String Pcode) {
        this.Pcode = Pcode;
    }
}
