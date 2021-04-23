package com.surveyapp.service.procedure;

import com.surveyapp.model.Code;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSummaryCommentDAO {
    private String procedureQuery = "{CALL summary_comment(?, ?)}";
    private Connection connection = null;
    private CallableStatement statement;
    private ResultSet resultSet = null;

    public GetSummaryCommentDAO setParameters(Code code) throws SQLException {
        // Get database connection
        connection = new DBUtil().getConnection();
        statement = connection.prepareCall(procedureQuery);

        //Set parameters
        statement.setString(1, code.getClazz().equals("null") ? null : code.getClazz());
        statement.setString(2, code.getLecturer().equals("null") ? null : code.getLecturer());

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
                    System.out.println("[LOGGER] GetSummaryQuestionDAO resultset: CLOSE");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                    System.out.println("[LOGGER] GetSummaryQuestionDAO statement: CLOSE");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("[LOGGER] GetSummaryQuestionDAO connection: CLOSE");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
    }
}
