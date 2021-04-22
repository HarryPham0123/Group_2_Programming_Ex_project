package com.surveyapp.service.ay_faculty_program;

import com.surveyapp.model.AYFacultyProgram;

import java.util.List;
import java.util.Optional;

public class AYFacultyProgramService {
    AYFacultyProgramDAO ayFacultyProgramDAO = new AYFacultyProgramDAO();

    public List<AYFacultyProgram> getAll() {
        return ayFacultyProgramDAO.getAll();
    }

    public AYFacultyProgram get(String academicYearCode) {
        Optional<AYFacultyProgram> ayFacultyProgram = ayFacultyProgramDAO.get(academicYearCode);
        return ayFacultyProgram.orElseGet(() -> new AYFacultyProgram());
    }

    public String save(AYFacultyProgram ayFacultyProgram) throws Exception{
        return ayFacultyProgramDAO.save(ayFacultyProgram);
    }

    public String delete(AYFacultyProgram ayFacultyProgram) throws Exception{
        return ayFacultyProgramDAO.delete(ayFacultyProgram);
    }
}
