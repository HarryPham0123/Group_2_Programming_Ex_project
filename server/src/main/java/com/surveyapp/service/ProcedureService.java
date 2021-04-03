package com.surveyapp.service;

import com.surveyapp.service.dao.GetQuestionnaireDAO;
import com.surveyapp.service.dao.ProcedureBaseDAO;
import com.surveyapp.service.dao.ProcedureDAO;
import com.surveyapp.service.dao.InsertQuestionnaireDAO;
import lombok.NonNull;

public class ProcedureService {
    private ProcedureBaseDAO procedureDAO = new ProcedureDAO("general_information").setParameters();
    private InsertQuestionnaireDAO questionnaireDAO = new InsertQuestionnaireDAO("insert_questionnaire");
    private GetQuestionnaireDAO getQuestionnaireDAO = new GetQuestionnaireDAO("get_answers");

    //Get all general information
    public String getAll() throws Exception {
        return procedureDAO.getAll();
    }

    public String insertAnswer(
            @NonNull String Lcode,
            @NonNull String Ccode,
            @NonNull String answer
    ) throws Exception {
        return questionnaireDAO.setParameters(Lcode, Ccode, answer).getAll();
    }

    public String getAnswer(
            String academicYear,
            String semester,
            String faculty,
            String program,
            String module,
            String lecturer,
            String clazz
    ) throws Exception {
        return getQuestionnaireDAO.setParameters(academicYear, semester, faculty, program, module, lecturer, clazz).getAll();
    }
}
