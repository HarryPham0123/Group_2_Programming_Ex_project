package com.surveyapp.service.academic_year_faculty;

import com.surveyapp.model.AcademicYearFaculty;

import java.util.List;
import java.util.Optional;

public class AcademicYearFacultyService {
    AcademicYearFacultyDAO academicYearFacultyDAO = new AcademicYearFacultyDAO();

    public List<AcademicYearFaculty> getAll() {
        return academicYearFacultyDAO.getAll();
    }

    public AcademicYearFaculty get(String academicYearCode) {
        Optional<AcademicYearFaculty> academicYearFaculty = academicYearFacultyDAO.get(academicYearCode);
        return academicYearFaculty.orElseGet(() -> new AcademicYearFaculty());
    }

    public String save(AcademicYearFaculty academicYearFaculty) throws Exception {
        return academicYearFacultyDAO.save(academicYearFaculty);
    }

    public String delete(AcademicYearFaculty academicYearFaculty) throws Exception {
        return academicYearFacultyDAO.delete(academicYearFaculty);
    }
}
