package com.surveyapp.service.procedure;

public class ProcedureDAO extends ProcedureBaseDAO {
    private String procedureQuery = "{CALL %s(%s, %s, %s, %s, %s, %s, %s)}";
    private String procedureName;

    public ProcedureDAO(String procedureName) {
        this.procedureName = procedureName;
    }

    public ProcedureDAO setParameters() {
        super.procedureQuery = String.format(
                this.procedureQuery, this.procedureName,
                "null", "null", "null", "null", "null", "null", "null");
        return this;
    }
}
