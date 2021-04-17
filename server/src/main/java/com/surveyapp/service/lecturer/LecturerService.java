package com.surveyapp.service.lecturer;

import com.surveyapp.model.Lecturer;

import java.util.List;
import java.util.Optional;

public class LecturerService {
    private LecturerDAO lecturerDAO = new LecturerDAO();

    public List<Lecturer> getAll() throws Exception {
        return lecturerDAO.getAll();
    }

    public Lecturer get(String code) throws Exception {
        Optional<Lecturer> lecturer = new LecturerDAO().get(code);
        return lecturer.orElseGet(() -> new Lecturer());
    }

    public void save(Lecturer lecturer) throws Exception {
         lecturerDAO.save(lecturer);
    }

    public void update(String code, Lecturer lecturer) throws Exception {
         lecturerDAO.update(code, lecturer);
    }

    public void delete(String code) throws Exception {
        lecturerDAO.delete(code);
    }
}
