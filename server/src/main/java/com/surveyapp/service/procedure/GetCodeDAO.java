package com.surveyapp.service.procedure;

import com.surveyapp.model.Code;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;
import lombok.NonNull;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCodeDAO {
    private Connection connection = null;
    private String procedureQuery = "{CALL get_code(?, ?, ?, ?, ?, ?, ?)}";
    private CallableStatement statement = null;
    private ResultSet resultSet = null;

    public GetCodeDAO setParameters(Code code) throws SQLException {
        //Get database connection
        connection = new DBUtil().getConnection();
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

