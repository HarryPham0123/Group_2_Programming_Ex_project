package com.surveyapp.service.clazz;

import com.surveyapp.model.Class;
import com.surveyapp.service.clazz.ClassDAO;

import java.util.List;
import java.util.Optional;

public class ClassService {
    ClassDAO classDAO = new ClassDAO();
    public List<Class> getAll() {
        return classDAO.getAll();
    }

    public Class get(String code) {
        Optional<Class> aClass = classDAO.get(code);
        return aClass.orElseGet(() -> new Class());
    }

    public boolean save(Class aClass) {
        return classDAO.save(aClass);
    }

    public boolean update(String code, Class aClass) {
        return classDAO.update(code, aClass);
    }

    public boolean delete(String code) {
        return classDAO.delete(code);
    }
}
