package com.surveyapp.service.procedure;

import com.surveyapp.model.Code;
import com.surveyapp.model.Questionnaire;

public class ProcedureService {
    /*FIXME: procedureDAO and getQuestionnaireDAO generics type*/
    private GetQuestionnaireDAO procedureDAO = new GetQuestionnaireDAO("general_information");
    private GetQuestionnaireDAO getQuestionnaireDAO = new GetQuestionnaireDAO("get_answers");
    private InsertQuestionnaireDAO insertQuestionnaireDAO = new InsertQuestionnaireDAO("insert_questionnaire");
    private GetCodeDAO getCodeDAO = new GetCodeDAO("get_code");

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
