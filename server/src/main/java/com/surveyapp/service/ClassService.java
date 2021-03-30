package com.surveyapp.service;

import com.surveyapp.model.Class;
import com.surveyapp.service.dao.ClassDAO;

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
}
