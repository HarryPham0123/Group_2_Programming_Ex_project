package com.surveyapp.model;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Lecturer {
    @Column(name = "Lcode")
    private String code;
    @Column(name = "Lname")
    private String name;
}
