package com.surveyapp.service;

import com.surveyapp.model.AcademicYear;
import com.surveyapp.service.dao.AcademicYearDAO;


import java.util.List;
import java.util.Optional;

public class AcademicYearService {
    private AcademicYearDAO academicYearDAO = new AcademicYearDAO();
    public List<AcademicYear> getAll() {
        return academicYearDAO.getAll();
    }
    public AcademicYear get(String code) {
        Optional<AcademicYear> academicYear = new AcademicYearDAO().get(code);
        return academicYear.orElseGet(() -> new AcademicYear());
    }

    public void save(AcademicYear academicYear) {
        academicYearDAO.save(academicYear);
    }
}
