package com.surveyapp.model;

import javax.persistence.Column;

public class Program {
    @Column(name = "Pcode")
    private String programCode;

    @Column(name = "Pname")
    private String programName;

    public Program(String programCode, String programName) {
        this.programCode = programCode;
        this.programName =programName;
    }

    public Program() {
        this(null, null);
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
