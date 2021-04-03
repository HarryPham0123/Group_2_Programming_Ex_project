package com.surveyapp.model;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Program {
    @Column(name = "Pcode")
    private String programCode;
    @Column(name = "Pname")
    private String programName;
}
