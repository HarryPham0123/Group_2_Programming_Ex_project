package com.surveyapp.service.faculty;

import com.surveyapp.model.Faculty;

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

    public boolean save(Faculty faculty) {
        return facultyDAO.save(faculty);
    }

    public boolean update(String code, Faculty faculty) {
        return facultyDAO.update(code, faculty);
    }

    public boolean delete(String code) {
        return facultyDAO.delete(code);
    }
}
