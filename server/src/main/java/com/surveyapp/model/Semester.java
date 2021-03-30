package com.surveyapp.model;


import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter

public class Semester {
    @Column(name = "Scode")
    private String code;
    @Column(name = "AYcode")
    private String AYCode;
}