package com.surveyapp.service.Login_test_Huy_Pham;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class LoginModel {
    @Column(name = "valid")
    private String Valid;
    @Column(name = "lecturer")
    private String Lcode;
    @Column(name = "class_code")
    private String CCode;
    @Column(name = "coordinator")
    private String coord;
    @Column(name = "program")
    private String proglist;
    @Column(name = "dean")
    private String dean;
    @Column(name = "faculty")
    private String faculty;

    public String getValid() {
        return Valid;
    }

    public String getCCode() {
        return CCode;
    }

    public String getCoord() {
        return coord;
    }

    public String getDean() {
        return dean;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getLcode() {
        return Lcode;
    }

    public String getProglist() {
        return proglist;
    }
    public List<String> LoginToString(){
        List<String> res =new ArrayList<>() ;
        res.add(getValid());
        res.add(getLcode());
        res.add(getCCode());
        res.add(getCoord());
        res.add(getProglist());
        res.add(getDean());
        res.add(getFaculty());
        return res;
    }
}
