package com.surveyapp.model;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Faculty {
    @Column(name = "Fcode")
    private String facultyCode;
    @Column(name = "Fname")
    private String facultyName;
}
