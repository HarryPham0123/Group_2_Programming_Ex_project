package com.surveyapp.service.procedure;

import com.surveyapp.model.Code;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetGeneralDAO {
    private Connection connection = null;
    private String procedureQuery = "{CALL general_information(?, ?, ?, ?, ?, ?, ?)}";
    private CallableStatement statement = null;
    private ResultSet resultSet = null;

    public GetGeneralDAO setParameters(Code code) throws SQLException {
        //Get database connection
        connection = new DBUtil().getConnection();
        statement = connection.prepareCall(procedureQuery);

        //Set parameters
        statement.setString(1, code.getAcademic_year().equals("null") ? null : code.getAcademic_year());
        statement.setString(2, code.getSemester().equals("null") ? null : code.getSemester());
        statement.setString(3, code.getFaculty().equals("null") ? null : code.getFaculty());
        statement.setString(4, code.getProgram().equals("null") ? null : code.getProgram());
        statement.setString(5, code.getModule().equals("null") ? null : code.getModule());
        statement.setString(6, code.getLecturer().equals("null") ? null : code.getLecturer());
        statement.setString(7, code.getClazz().equals("null") ? null : code.getClazz());

        return this;
    }

    public String executeProcedure() throws Exception {
        try {
            System.out.println("[LOGGER] Query executed: " + procedureQuery);
            resultSet = statement.executeQuery();
            String result = ObjectConverter.toJSON(resultSet);
            return result;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    System.out.println("[LOGGER] GetCodeDAO resultset: CLOSE");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                    System.out.println("[LOGGER] GetCodeDAO statement: CLOSE");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("[LOGGER] GetCodeDAO connection: CLOSE");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
    }
}
