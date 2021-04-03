package com.surveyapp.controller;
import com.surveyapp.model.Semester;
import com.surveyapp.service.SemesterService;
import java.util.List;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/semesters")
public class SemesterRoute {
    private SemesterService semesterService = new SemesterService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Semester> getAll() {
        List<Semester> semesterList = semesterService.getAll();
        return semesterList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Semester getByCode(@PathParam("code") String code) {
        Semester semester = semesterService.get(code);
        return semester;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String insert(Semester semester) {
        return "Underconstruction!";
    }
}
