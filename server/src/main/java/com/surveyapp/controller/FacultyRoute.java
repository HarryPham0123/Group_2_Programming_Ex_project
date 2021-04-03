package com.surveyapp.controller;


import com.surveyapp.model.Faculty;
import com.surveyapp.service.FacultyService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/faculties")
public class FacultyRoute {
    private FacultyService facultyService = new FacultyService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Faculty> getAll(){
        List<Faculty> facultyList = facultyService.getAll();
        return facultyList;
    }

}
