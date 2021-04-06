package com.surveyapp.model;

import javax.persistence.Column;

public class Class {
    @Column(name = "Ccode")
    private String code;

    @Column(name = "size")
    private int size;

    @Column(name = "Scode")
    private String Scode;

    @Column(name = "Mcode")
    private String Mcode;

    public Class(String code, int size, String Scode, String Mcode) {
        this.code = code;
        this.size = size;
        this.Scode = Scode;
        this.Mcode = Mcode;
    }

    public Class() {
        this(null, 0, null, null);
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getScode() {
        return Scode;
    }

    public void setScode(String scode) {
        Scode = scode;
    }

    public String getMcode() {
        return Mcode;
    }

    public void setMcode(String mcode) {
        Mcode = mcode;
    }
}
