package com.surveyapp.service.module;

import com.surveyapp.model.Module;
import com.surveyapp.service.module.ModuleDAO;

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

    public boolean save(Module module) {
        return DAO.save(module);
    }

    public boolean update(String code, Module module) {
        return DAO.update(code, module);
    }

    public boolean delete(String code) {
        return DAO.delete(code);
    }
}
