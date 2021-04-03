package com.surveyapp.model;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class AcademicYear {
    @Column(name = "AYcode")
    private String code;
    public String getCode(){
        return code;
    }
}

