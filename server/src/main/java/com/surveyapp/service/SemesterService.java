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
}
