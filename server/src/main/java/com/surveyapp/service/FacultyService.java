package com.surveyapp.service;

import com.surveyapp.model.Faculty;
import com.surveyapp.service.dao.FacultyDAO;

import java.util.List;
import java.util.Optional;

public class FacultyService {
    private FacultyDAO facultyDAO = new FacultyDAO();
    public List<Faculty> getAll() {
        return facultyDAO.getAll();
    }
    public Faculty get(String code) {
        Optional<Faculty> faculty = new FacultyDAO().get(code);
        return faculty.orElseGet(() -> new Faculty());
    }

    public void save(Faculty faculty) {
        facultyDAO.save(faculty);
    }

    public void update(String code, Faculty faculty) {
        facultyDAO.update(code, faculty);
    }

    public void delete(String code) {
        facultyDAO.delete(code);
    }
}
