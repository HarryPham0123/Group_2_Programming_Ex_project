package com.surveyapp.service.academic_year;

import com.surveyapp.model.AcademicYear;
import com.surveyapp.service.academic_year.AcademicYearDAO;


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

    public boolean save(AcademicYear academicYear) {
        return academicYearDAO.save(academicYear);
    }

    public boolean update(String code, AcademicYear academicYear) {
        return academicYearDAO.update(code, academicYear);
    }

    public boolean delete(String code) {
        return academicYearDAO.delete(code);
    }
}
