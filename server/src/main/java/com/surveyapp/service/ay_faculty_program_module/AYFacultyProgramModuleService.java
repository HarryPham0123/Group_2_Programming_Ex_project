package com.surveyapp.service.ay_faculty_program_module;

import com.surveyapp.model.AYFacultyProgramModule;

import java.util.List;
import java.util.Optional;

public class AYFacultyProgramModuleService {
    private AYFacultyProgramModuleDAO ayFacultyProgramModuleDAO = new AYFacultyProgramModuleDAO();

    public List<AYFacultyProgramModule> getAll() {
        return ayFacultyProgramModuleDAO.getAll();
    }

    public AYFacultyProgramModule get(String academicYearCode) {
        Optional<AYFacultyProgramModule> ayFacultyProgramModule = ayFacultyProgramModuleDAO.get(academicYearCode);
        return ayFacultyProgramModule.orElseGet(() -> new AYFacultyProgramModule());
    }

    public String save(AYFacultyProgramModule ayFacultyProgramModule) throws Exception{
        return ayFacultyProgramModuleDAO.save(ayFacultyProgramModule);
    }

    public String delete(AYFacultyProgramModule ayFacultyProgramModule) throws Exception{
        return ayFacultyProgramModuleDAO.delete(ayFacultyProgramModule);
    }
}
