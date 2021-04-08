package com.surveyapp.model;

import javax.persistence.Column;

public class Program {
    @Column(name = "Pcode")
    private String code;

    @Column(name = "Pname")
    private String name;

    public Program(String code, String name) {
        this.code = code;
        this.name =name;
    }

    public Program() {
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
