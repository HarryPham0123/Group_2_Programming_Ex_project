package com.surveyapp.service.module;

import com.surveyapp.model.Module;
import com.surveyapp.service.module.ModuleDAO;

import java.util.List;
import java.util.Optional;

public class ModuleService {
    private ModuleDAO DAO = new ModuleDAO();
    public List<Module> getAll()throws Exception {
        return DAO.getAll();
    }
    public Module get(String code)throws Exception {
        Optional<Module> module = new ModuleDAO().get(code);
        return module.orElseGet(() -> new Module());
    }

    public void save(Module module)throws Exception {
        DAO.save(module);
    }

    public void update(String code, Module module)throws Exception {
        DAO.update(code, module);
    }

    public void delete(String code)throws Exception {
        DAO.delete(code);
    }
}
