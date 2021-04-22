package com.surveyapp.service.clazz;

import com.surveyapp.model.Class;
import com.surveyapp.service.clazz.ClassDAO;

import java.util.List;
import java.util.Optional;

public class ClassService {
    ClassDAO classDAO = new ClassDAO();
    public List<Class> getAll() throws Exception {
        return classDAO.getAll();
    }

    public Class get(String code) throws Exception{
        Optional<Class> aClass = classDAO.get(code);
        return aClass.orElseGet(() -> new Class());
    }

    public void save(Class aClass) throws Exception {
        classDAO.save(aClass);
    }

    public void update(String code, Class aClass) throws Exception {
        classDAO.update(code, aClass);
    }

    public void delete(String code) throws Exception {
        classDAO.delete(code);
    }
}
