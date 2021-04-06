package com.surveyapp.service.program;

import com.surveyapp.model.Program;

import java.util.List;

public class ProgramService {
    private ProgramDAO programDAO = new ProgramDAO();
    public List<Program> getAll() {
        return programDAO.getAll();
    }
}
