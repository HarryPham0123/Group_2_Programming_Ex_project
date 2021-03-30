package com.surveyapp.model;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Module {
    @Column(name = "Mcode")
    private String code;
    @Column(name = "Mname")
    private String name;
}
