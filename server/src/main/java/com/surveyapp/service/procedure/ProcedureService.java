package com.surveyapp.service.procedure;

import com.surveyapp.model.Code;
import com.surveyapp.model.Questionnaire;

public class ProcedureService {
    private ProcedureBaseDAO procedureDAO = new ProcedureDAO("general_information").setParameters();
    private GetQuestionnaireDAO getQuestionnaireDAO = new GetQuestionnaireDAO("get_answers");
    private InsertQuestionnaireDAO insertQuestionnaireDAO = new InsertQuestionnaireDAO("insert_questionnaire");
    private GetCodeDAO getCodeDAO = new GetCodeDAO("get_code");

    //Get all general information
    public String getAll() throws Exception {
        return procedureDAO.executeProcedure();
    }

    //Insert new questionnaire
    public String insertQuestionnaire(Questionnaire questionnaire) throws Exception {
        return insertQuestionnaireDAO.setParameters(questionnaire).executeProcedure();
    }

    //Get questionnaire with corresponding code
    public String getQuestionnaire(Code code) throws Exception {
        return getQuestionnaireDAO.setParameters(code).executeProcedure();
    }

    //Retrieves all codes
    public String getCode(Code code) throws Exception {
        return getCodeDAO.setParameters(code).executeProcedure();
    }
}
