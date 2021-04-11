package com.surveyapp.service.procedure;

import com.surveyapp.model.Code;
import com.surveyapp.model.Questionnaire;

public class ProcedureService {
    private GetGeneralDAO procedureDAO = new GetGeneralDAO();
    private GetQuestionnaireDAO getQuestionnaireDAO = new GetQuestionnaireDAO();
    private InsertQuestionnaireDAO insertQuestionnaireDAO = new InsertQuestionnaireDAO();
    private GetCodeDAO getCodeDAO = new GetCodeDAO();

    //Get all general information
    public String getAll(Code code) throws Exception {
        return procedureDAO.setParameters(code).executeProcedure();
    }

    //Insert new questionnaire
    public String insertQuestionnaire(Questionnaire questionnaire) throws  Exception{
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
