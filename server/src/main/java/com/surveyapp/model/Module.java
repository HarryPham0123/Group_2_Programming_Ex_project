package com.surveyapp.model;

import javax.persistence.Column;


public class Module {
    @Column(name = "Mcode")
    private String code;

    @Column(name = "Mname")
    private String name;

    public Module(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Module() {
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
