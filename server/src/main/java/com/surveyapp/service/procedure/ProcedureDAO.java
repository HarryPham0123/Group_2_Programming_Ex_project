package com.surveyapp.service.procedure;

import com.surveyapp.model.Code;

import java.sql.SQLException;

public class ProcedureDAO extends ProcedureBaseDAO {
    private String procedureQuery = "{CALL %s(?, ?, ?, ?, ?, ?, ?)}";
    private String procedureName;

    public ProcedureDAO(String procedureName) {
        this.procedureName = procedureName;

        //Set table name
        this.procedureQuery = String.format(this.procedureQuery, this.procedureName);
    }

    public ProcedureDAO setParameters(Code code) throws SQLException {
        //Get database connection
        statement = connection.prepareCall(procedureQuery);

        //Set parameters
        statement.setString(1, code.getAcademic_year());
        statement.setString(2, code.getSemester());
        statement.setString(3, code.getFaculty());
        statement.setString(4, code.getProgram());
        statement.setString(5, code.getModule());
        statement.setString(6, code.getClazz());
        statement.setString(7, code.getLecturer());
        super.procedureQuery = String.format(
                this.procedureQuery, this.procedureName,
                "null", "null", "null", "null", "null", "null", "null");
        return this;
    }
}
