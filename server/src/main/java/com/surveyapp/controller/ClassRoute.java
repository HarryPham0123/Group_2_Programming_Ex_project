package com.surveyapp.controller;
import com.surveyapp.model.Class;
import com.surveyapp.model.Semester;
import com.surveyapp.service.ClassService;
import com.surveyapp.service.SemesterService;
import java.util.List;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/classes")
public class ClassRoute {
    private ClassService classService = new ClassService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Class> getAll() {
        List<Class> classList = classService.getAll();
        return classList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Class getByCode(@PathParam("code") String code) {
        Class aClass = classService.get(code);
        return aClass;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String insert(Semester semester) {
        return "Underconstruction!";
    }
}
