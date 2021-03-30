package com.surveyapp.service;

import com.surveyapp.model.Faculty;
import com.surveyapp.service.dao.FacultyDAO;

import java.util.List;

public class FacultyService {
    private FacultyDAO facultyDAO = new FacultyDAO();
    public List<Faculty> getAll() {
        return facultyDAO.getAll();
    }
}
