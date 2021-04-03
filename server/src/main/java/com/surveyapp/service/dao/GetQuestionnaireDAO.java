package com.surveyapp.service.dao;

import lombok.NonNull;

public class GetQuestionnaireDAO extends ProcedureBaseDAO {
    private String procedureQuery = "{CALL %s(%s, %s, %s, %s, %s, %s, %s)}";
    private String procedureName;

    public GetQuestionnaireDAO(@NonNull String procedureName) {
        this.procedureName = procedureName;
    }

    public GetQuestionnaireDAO setParameters(
            String academicYear,
            String semester,
            String faculty,
            String program,
            String module,
            String lecturer,
            String clazz) {
        academicYear = academicYear.equals("null") ? null : '\''+ academicYear +'\'';
        semester = semester.equals("null") ? null : '\''+ semester +'\'';
        faculty = faculty.equals("null") ? null : '\''+ faculty +'\'';
        program = program.equals("null") ? null : '\''+ program +'\'';
        module = module.equals("null") ? null : '\''+ module +'\'';
        lecturer = lecturer.equals("null") ? null : '\''+ lecturer +'\'';
        clazz = clazz.equals("null") ? null : '\''+ clazz +'\'';
        super.procedureQuery = String.format(this.procedureQuery, this.procedureName, academicYear, semester, faculty, program, module, lecturer, clazz);
        return this;
    }
}
