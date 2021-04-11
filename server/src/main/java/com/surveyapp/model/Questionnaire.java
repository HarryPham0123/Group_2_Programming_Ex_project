package com.surveyapp.model;

import java.util.ArrayList;

public class Questionnaire {
    private String lcode;
    private String ccode;
    private ArrayList<QuestionAnswer> question_list;

    public String getLcode() {
        return lcode;
    }

    public void setLcode(String lcode) {
        this.lcode = lcode;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public ArrayList<QuestionAnswer> getQuestion_list() {
        return question_list;
    }

    public void setQuestion_list(ArrayList<QuestionAnswer> question_list) {
        this.question_list = question_list;
    }
}