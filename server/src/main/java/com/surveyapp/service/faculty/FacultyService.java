package com.surveyapp.service.faculty;

import com.surveyapp.model.Faculty;

import java.util.List;
import java.util.Optional;

public class FacultyService {
    private FacultyDAO facultyDAO = new FacultyDAO();
    public List<Faculty> getAll() throws Exception {
        return facultyDAO.getAll();
    }
    public Faculty get(String code) throws Exception {
        Optional<Faculty> faculty = new FacultyDAO().get(code);
        return faculty.orElseGet(() -> new Faculty());
    }

    public void save(Faculty faculty) throws Exception{
         facultyDAO.save(faculty);
    }

    public void update(String code, Faculty faculty) throws Exception {
         facultyDAO.update(code, faculty);
    }

    public void delete(String code) throws Exception {
         facultyDAO.delete(code);
    }
}
