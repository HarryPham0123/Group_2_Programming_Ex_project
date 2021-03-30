package com.surveyapp.service;

import com.surveyapp.model.AcademicYear;
import com.surveyapp.model.Module;
import com.surveyapp.service.dao.AcademicYearDAO;
import com.surveyapp.service.dao.ModuleDAO;

import java.util.List;
import java.util.Optional;

public class ModuleService {
    private ModuleDAO DAO = new ModuleDAO();
    public List<Module> getAll() {
        return DAO.getAll();
    }
    public Module get(String code) {
        Optional<Module> module = new ModuleDAO().get(code);
        return module.orElseGet(() -> new Module());
    }
}
