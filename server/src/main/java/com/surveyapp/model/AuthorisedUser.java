package com.surveyapp.model;

public class AuthorisedUser {
    private String lecturerCode;
    private String classCode;
    private int coordinatorCode;
    private String program;
    private int deanCode;
    private String faculty;

    public AuthorisedUser(String lecturerCode,
                          String classCode,
                          int coordinatorCode,
                          String program,
                          int deanCode,
                          String faculty) {
        this.lecturerCode = lecturerCode;
        this.classCode = classCode;
        this.coordinatorCode = coordinatorCode;
        this.program = program;
        this.deanCode = deanCode;
        this.faculty = faculty;

    }

    public String getLecturerCode() {
        return lecturerCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public int getCoordinatorCode() {
        return coordinatorCode;
    }

    public String getProgram() {
        return program;
    }

    public int getDeanCode() {
        return deanCode;
    }

    public String getFaculty() {
        return faculty;
    }
}
