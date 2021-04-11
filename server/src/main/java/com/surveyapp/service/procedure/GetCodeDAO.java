package com.surveyapp.service.procedure;

import com.surveyapp.model.Code;
import lombok.NonNull;

import java.sql.SQLException;

public class GetCodeDAO extends ProcedureBaseDAO {
    private String procedureQuery = "{CALL get_code(?, ?, ?, ?, ?, ?, ?)}";

    public GetCodeDAO setParameters(Code code) throws SQLException {
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

        return this;
    }
}

