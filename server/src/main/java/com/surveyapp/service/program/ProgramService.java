package com.surveyapp.service.program;

import com.surveyapp.model.Program;

import java.util.List;

public class ProgramService {
    private ProgramDAO programDAO = new ProgramDAO();
    public List<Program> getAll() {
        return programDAO.getAll();
    }
    public Program get(String code) {
        Optional<Program> program = new ProgramDAO().get(code);
        return program.orElseGet(() -> new Program());
    }

    public void save(Program program) {
        programDAO.save(program);
    }

    public void update(String code, Program program) {
        programDAO.update(code, program);
    }

    public void delete(String code) {
        programDAO.delete(code);
    }
    
}
