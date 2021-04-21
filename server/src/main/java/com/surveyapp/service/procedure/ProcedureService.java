package com.surveyapp.service.procedure;

import com.surveyapp.model.Code;
import com.surveyapp.model.Questionnaire;

public class ProcedureService {
    private GetGeneralDAO procedureDAO = new GetGeneralDAO();
    private InsertQuestionnaireDAO insertQuestionnaireDAO = new InsertQuestionnaireDAO();
    private GetQuestionnaireDAO getQuestionnaireDAO = new GetQuestionnaireDAO();
    private GetCodeDAO getCodeDAO = new GetCodeDAO();
    private GetSummaryAttendanceDAO getSummaryAttendanceDAO= new GetSummaryAttendanceDAO();
    private GetSummaryGenderDAO getSummaryGenderDAO = new GetSummaryGenderDAO();
    private GetSummaryQuestionDAO getSummaryQuestionDAO = new GetSummaryQuestionDAO();
    //Get all general information
    public String getAll(Code code) throws Exception {
        return procedureDAO.setParameters(code).executeProcedure();
    }

    //Insert new questionnaire
    public String insertQuestionnaire(Questionnaire questionnaire) throws  Exception {
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

    // Retrieve Summary Attendance
    public String getSummaryAttendance(Code code) throws Exception {
        return getSummaryAttendanceDAO.setParameters(code).executeProcedure();
    }

    // Retrieve Summary Gender
    public String getSummaryGender(Code code) throws Exception {
        return getSummaryGenderDAO.setParameters(code).executeProcedure();
    }

    //Retrieve Summary Questions
    public String getSummaryQuestion(Code code, String questionNumber) throws Exception {
        return getSummaryQuestionDAO.setParameters(code, questionNumber).executeProcedure();
    }
}
