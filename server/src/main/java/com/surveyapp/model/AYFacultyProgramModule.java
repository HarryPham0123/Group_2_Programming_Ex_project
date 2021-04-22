package com.surveyapp.model;

import javax.persistence.Column;

public class AYFacultyProgramModule {
    @Column(name = "AYcode")
    private String AYcode;

    @Column(name = "Fcode")
    private String Fcode;

    @Column(name = "Pcode")
    private String Pcode;

    @Column(name = "Mcode")
    private String Mcode;

    public AYFacultyProgramModule() {
        this.AYcode = "";
        this.Fcode = "";
        this.Pcode = "";
        this.Mcode = "";
    }

    public AYFacultyProgramModule(String AYcode, String Fcode, String Pcode, String Mcode) {
        this.AYcode = AYcode;
        this.Fcode = Fcode;
        this.Pcode = Pcode;
        this.Mcode = Mcode;
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

    public String getMcode() {
        return Mcode;
    }

    public void setAYcode(String AYcode) {
        this.AYcode = AYcode;
    }

    public void setFcode(String fcode) {
        Fcode = fcode;
    }

    public void setPcode(String pcode) {
        Pcode = pcode;
    }

    public void setMcode(String mcode) {
            Mcode = mcode;
    }
}
