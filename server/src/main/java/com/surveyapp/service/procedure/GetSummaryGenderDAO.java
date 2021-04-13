package com.surveyapp.service.procedure;

import com.surveyapp.model.Code;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSummaryGenderDAO{
    private String procedureQuery = "{CALL summary_gender(?, ?, ?, ?, ?, ?, ?)}";
    private Connection connection = new DBUtil().getConnection();
    private CallableStatement statement;
    private ResultSet resultSet = null;

    public GetSummaryGenderDAO setParameters(Code code) throws SQLException {
        // Get database connection
        statement = connection.prepareCall(procedureQuery);

        //Set parameters
        statement.setString(1, code.getAcademic_year());
        statement.setString(2, code.getSemester());
        statement.setString(3, code.getFaculty());
        statement.setString(4, code.getProgram());
        statement.setString(5, code.getModule());
        statement.setString(6, code.getLecturer());
        statement.setString(7, code.getClazz());

        return this;
    }

    public String executeProcedure() throws Exception {
        try {
            System.out.println("[LOGGER] Query executed: " + procedureQuery);
            resultSet = statement.executeQuery();
            String result = ObjectConverter.toSummaryJSON(resultSet);
            return result;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    System.out.println("[LOGGER] Database resultset: CLOSE");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                    System.out.println("[LOGGER] Database statement: CLOSE");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("[LOGGER] Database connection: CLOSE");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
    }
}