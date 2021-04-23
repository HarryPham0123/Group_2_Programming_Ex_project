package com.surveyapp.model;

import javax.persistence.Column;

public class LecturerClass {

    @Column(name = "Lcode")
    private String Lcode;

    @Column(name = "Ccode")
    private String Ccode;

    public LecturerClass(String lecturerCode, String classCode) {
        this.Lcode = lecturerCode;
        this.Ccode = classCode;
    }

    public LecturerClass() {
        this.Lcode = "";
        this.Ccode = "";
    }

    public String getLcode() {
        return Lcode;
    }

    public String getCcode() {
        return Ccode;
    }

    public void setLcode(String lecturerCode) {
        this.Lcode = lecturerCode;
    }

    public void setCcode(String classCode) {
        this.Ccode = classCode;
    }

}
