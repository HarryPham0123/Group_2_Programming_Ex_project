package com.surveyapp.service;

import com.surveyapp.service.dao.ProcedureDAO;

public class ProcedureService {
    private ProcedureDAO procedureDAO = new ProcedureDAO("general_information");
    public String getAll() {
        return procedureDAO.getAll();
    }
}
