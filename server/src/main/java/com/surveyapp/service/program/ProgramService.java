package com.surveyapp.service.program;

import com.surveyapp.model.Program;

import java.util.List;
import java.util.Optional;


public class ProgramService {
    private ProgramDAO programDAO = new ProgramDAO();
    public List<Program> getAll()throws Exception {
        return programDAO.getAll();
    }
    public Program get(String code) throws Exception {
        Optional<Program> program = new ProgramDAO().get(code);
        return program.orElseGet(() -> new Program());
    }

    public void save(Program program)throws Exception {
         programDAO.save(program);
    }

    public void update(String code, Program program)throws Exception {
         programDAO.update(code, program);
    }

    public void delete(String code)throws Exception {
        programDAO.delete(code);
    }

}
