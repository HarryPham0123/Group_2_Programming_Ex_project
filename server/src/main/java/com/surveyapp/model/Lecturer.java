package com.surveyapp.model;

import javax.persistence.Column;

public class Lecturer {
    @Column(name = "Lcode")
    private String code;

    @Column(name = "Lname")
    private String name;

    @Column(name = "user_id")
    private String userID;

    public Lecturer(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Lecturer() {
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
