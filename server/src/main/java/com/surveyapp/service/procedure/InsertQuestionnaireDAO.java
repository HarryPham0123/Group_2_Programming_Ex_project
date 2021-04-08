package com.surveyapp.service.procedure;

import com.surveyapp.model.Questionnaire;

import java.sql.SQLException;

public class InsertQuestionnaireDAO extends ProcedureBaseDAO {
    private String procedureQuery = "{CALL insert_questionnaire(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

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

        //Logger
        super.procedureQuery = procedureQuery;
        return this;
    }
}
