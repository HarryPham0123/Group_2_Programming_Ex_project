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
        return academic_year.equals("null") ? null : academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }

    public String getSemester() {
        return semester.equals("null") ? null : semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getFaculty() {
        return faculty.equals("null") ? null : faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getProgram() {
        return program.equals("null") ? null : program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getModule() {
        return module.equals("null") ? null : module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getLecturer() {
        return lecturer.equals("null") ? null : lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getClazz() {
        return clazz.equals("null") ? null : clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
