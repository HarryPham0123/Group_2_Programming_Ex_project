package com.surveyapp.model;

public class Code {
    private String academic_year;
    private String semester;
    private String faculty;
    private String program;
    private String module;
    private String lecturer;
    private String clazz;

    public Code(String academic_year, String semester, String faculty, String program, String module, String lecturer, String clazz) {
        this.academic_year = academic_year;
        this.semester = semester;
        this.faculty = faculty;
        this.program = program;
        this.module = module;
        this.lecturer = lecturer;
        this.clazz = clazz;
    }

    public Code() {
        this(null, null, null, null, null, null, null);
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
