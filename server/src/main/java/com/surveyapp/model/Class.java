package com.surveyapp.model;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Class {
    @Column(name = "Ccode")
    private String code;
    @Column(name = "size")
    private int size;
    @Column(name = "Scode")
    private String Scode;
    @Column(name = "Mcode")
    private String Mcode;
}