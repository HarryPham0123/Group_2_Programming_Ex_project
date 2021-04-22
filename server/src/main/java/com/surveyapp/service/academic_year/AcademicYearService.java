package com.surveyapp.service.academic_year;

import com.surveyapp.model.AcademicYear;
import com.surveyapp.service.academic_year.AcademicYearDAO;


import java.util.List;
import java.util.Optional;

public class AcademicYearService {
    private AcademicYearDAO academicYearDAO = new AcademicYearDAO();
    public List<AcademicYear> getAll()throws Exception {
        return academicYearDAO.getAll();
    }
    public AcademicYear get(String code) throws Exception {
        Optional<AcademicYear> academicYear = new AcademicYearDAO().get(code);
        return academicYear.orElseGet(() -> new AcademicYear());
    }

    public void save(AcademicYear academicYear) throws Exception{
        academicYearDAO.save(academicYear);
    }

    public void update(String code, AcademicYear academicYear)throws Exception {
        academicYearDAO.update(code, academicYear);
    }

    public void delete(String code) throws Exception {
        academicYearDAO.delete(code);
    }
}
