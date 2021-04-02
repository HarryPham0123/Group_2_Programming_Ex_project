package com.surveyapp.service;

import com.surveyapp.service.dao.ProcedureDAO;

public class ProcedureService {
    private ProcedureDAO procedureDAO = new ProcedureDAO();

    public String getAll() {
        return procedureDAO.getAll();
    }

    public String getAcademicYear(String code) {
        return procedureDAO.getAcademicYear(code);
    }

    public String getSemester(String code) {
        return procedureDAO.getSemester(code);
    }

    public String getFaculty(String code) {
        return procedureDAO.getFaculty(code);
    }

    public String getProgram(String code) {
        return procedureDAO.getProgram(code);
    }

    public String getModule(String code) {
        return procedureDAO.getModule(code);
    }

    public String getLecturer(String code) {
        return procedureDAO.getLecturer(code);
    }

    public String getClass(String code) {
        return procedureDAO.getClass(code);
    }
}
