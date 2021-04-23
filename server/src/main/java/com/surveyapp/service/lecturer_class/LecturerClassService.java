package com.surveyapp.service.lecturer_class;

import com.surveyapp.model.LecturerClass;

import java.util.List;
import java.util.Optional;

public class LecturerClassService {
    LecturerClassDAO lecturerClassDAO = new LecturerClassDAO();

    public List<LecturerClass> getAll(){
        return lecturerClassDAO.getAll();
    }

    public LecturerClass get(String lecturerCode){
        Optional<LecturerClass> lecturerClass = lecturerClassDAO.get(lecturerCode);
        return lecturerClass.orElseGet(() -> new LecturerClass());
    }

    public String save(LecturerClass lecturerClass) throws Exception{
        return lecturerClassDAO.save(lecturerClass);
    }

    public String delete(LecturerClass lecturerClass) throws Exception{
        return lecturerClassDAO.delete(lecturerClass);
    }


}
