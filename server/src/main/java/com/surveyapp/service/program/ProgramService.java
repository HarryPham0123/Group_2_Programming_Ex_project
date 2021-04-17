package com.surveyapp.service.program;

import com.surveyapp.model.Program;

import java.util.List;
import java.util.Optional;


public class ProgramService {
    private ProgramDAO programDAO = new ProgramDAO();
    public List<Program> getAll() {
        return programDAO.getAll();
    }
    public Program get(String code) {
        Optional<Program> program = new ProgramDAO().get(code);
        return program.orElseGet(() -> new Program());
    }

    public boolean save(Program program) {
        return programDAO.save(program);
    }

    public boolean update(String code, Program program) {
        return programDAO.update(code, program);
    }

    public boolean delete(String code) {
        return programDAO.delete(code);
    }

}
