package com.surveyapp.service;

import com.surveyapp.model.Semester;
import com.surveyapp.service.dao.SemesterDAO;

import java.util.List;
import java.util.Optional;

public class SemesterService {
    SemesterDAO semesterDAO = new SemesterDAO();
    public List<Semester> getAll() {
        return semesterDAO.getAll();
    }
    
    public Semester get(String code) {
        Optional<Semester> semester = semesterDAO.get(code);
        return semester.orElseGet(() -> new Semester());
    }
    
    public void save(Semester semester) {
        semesterDAO.save(semester);
    }

    public void update(String code, Semester semester) {
        semesterDAO.update(code, semester);
    }

    public void delete(String code) {
        semesterDAO.delete(code);
    }
}
