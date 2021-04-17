package com.surveyapp.service.semester;

import com.surveyapp.model.Semester;

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

    public boolean save(Semester semester) {
        return semesterDAO.save(semester);
    }

    public boolean update(String code, Semester semester) {
        return semesterDAO.update(code, semester);
    }

    public boolean delete(String code) {
        return semesterDAO.delete(code);
    }
}
