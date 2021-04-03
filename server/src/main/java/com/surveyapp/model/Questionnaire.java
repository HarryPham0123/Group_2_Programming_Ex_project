package com.surveyapp.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Questionnaire {
    private String lcode;
    private String ccode;
    private Questions answer;
}
