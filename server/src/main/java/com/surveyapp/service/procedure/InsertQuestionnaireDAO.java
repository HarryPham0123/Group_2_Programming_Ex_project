package com.surveyapp.service.procedure;

import com.surveyapp.model.Questionnaire;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertQuestionnaireDAO {
    private Connection connection = new DBUtil().getConnection();
    private String procedureQuery = "{CALL insert_questionnaire(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private CallableStatement statement = null;
    private ResultSet resultSet = null;

    public InsertQuestionnaireDAO setParameters(Questionnaire questionnaire) throws SQLException {
        //Get database connection
        statement = connection.prepareCall(procedureQuery);

        //Set lcode and ccode
        statement.setString(1, questionnaire.getLcode());
        statement.setString(2, questionnaire.getCcode());

        //Set questions with corresponding answer
        for (int index = 3; index <= 22; index++) {
            Object questionAnswer = questionnaire.getQuestion_list().get(index - 3).getAnswer();
            statement.setObject(index, questionAnswer);
        }
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
