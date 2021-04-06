package com.surveyapp.service.procedure;

import com.surveyapp.model.Questionnaire;
import lombok.NonNull;

import java.sql.SQLException;

public class InsertQuestionnaireDAO extends ProcedureBaseDAO {
    private String procedureQuery = "{CALL %s(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private String procedureName;

    public InsertQuestionnaireDAO(@NonNull String procedureName) {
        this.procedureName = procedureName;
    }

    public InsertQuestionnaireDAO setParameters(Questionnaire questionnaire) throws SQLException {
        //Set table-name
        this.procedureQuery = String.format(this.procedureQuery, this.procedureName);

        //Get database connection
        statement = connection.prepareCall(procedureQuery);

        //Set lcode-ccode
        statement.setString(1, questionnaire.getLcode());
        statement.setString(2, questionnaire.getCcode());

        //Set questions with corresponding answer
        for (int index = 3; index <= 23; index++) {
            Object questionAnswer = questionnaire.getQuestion_list().get(index - 3).getAnswer();
            statement.setObject(index, questionAnswer);
        }

        return this;
    }
}
