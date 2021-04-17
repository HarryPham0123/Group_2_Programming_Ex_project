package com.surveyapp.service.lecturer;

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

    public boolean save(Lecturer lecturer) {
        return lecturerDAO.save(lecturer);
    }

    public boolean update(String code, Lecturer lecturer) {
        return lecturerDAO.update(code, lecturer);
    }

    public boolean delete(String code) {
        return lecturerDAO.delete(code);
    }
}
