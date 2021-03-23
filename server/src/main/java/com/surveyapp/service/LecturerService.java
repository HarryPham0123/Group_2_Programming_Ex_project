package com.surveyapp.service;

import com.surveyapp.model.Lecturer;

import java.util.List;
import java.util.Optional;

public class LecturerService {
    private LecturerDAO lecturerDAO = new LecturerDAO();
    public List<Lecturer> getAll() {
        return lecturerDAO.getAll();
    }
    public Lecturer get(String code) {
        Optional<Lecturer> lecturer = new LecturerDAO().get(code);
        return lecturer.orElseGet(() -> new Lecturer());
    }
}
