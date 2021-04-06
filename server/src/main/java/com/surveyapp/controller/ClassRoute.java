package com.surveyapp.controller;
import com.surveyapp.model.Class;
import com.surveyapp.service.clazz.ClassService;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
}
