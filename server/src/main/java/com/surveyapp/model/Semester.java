package com.surveyapp.model;


import lombok.*;

import javax.persistence.Column;

public class Semester {
    @Column(name = "Scode")
    private String code;

    @Column(name = "AYcode")
    private String AYCode;

    public Semester(String code, String AYCode) {
        this.code = code;
        this.AYCode = AYCode;
    }

    public Semester() {
        this(null, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAYCode() {
        return AYCode;
    }

    public void setAYCode(String AYCode) {
        this.AYCode = AYCode;
    }
}
