package com.surveyapp.service.semester;

import com.surveyapp.model.Semester;

import java.util.List;
import java.util.Optional;

public class SemesterService {
    SemesterDAO semesterDAO = new SemesterDAO();
    public List<Semester> getAll() throws Exception {
        return semesterDAO.getAll();
    }

    public Semester get(String code) throws Exception {
        Optional<Semester> semester = semesterDAO.get(code);
        return semester.orElseGet(() -> new Semester());
    }

    public void save(Semester semester)throws Exception  {
        semesterDAO.save(semester);
    }

    public void update(String code, Semester semester)throws Exception  {
        semesterDAO.update(code, semester);
    }

    public void delete(String code)throws Exception  {
        semesterDAO.delete(code);
    }
}
