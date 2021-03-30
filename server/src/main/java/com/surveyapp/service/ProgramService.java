package com.surveyapp.service;

import com.surveyapp.model.Program;
import com.surveyapp.service.dao.ProgramDAO;

import java.util.List;

public class ProgramService {
    private ProgramDAO programDAO = new ProgramDAO();
    public List<Program> getAll() {
        return programDAO.getAll();
    }
}
