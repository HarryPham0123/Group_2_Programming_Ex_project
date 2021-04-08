package com.surveyapp.controller;
import com.surveyapp.model.Class;
import com.surveyapp.service.clazz.ClassService;

import java.util.List;

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
    public Response insert(Class aClass) {
        classService.save(aClass);
        return Response.ok().entity("New class successfully inserted").build();
    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Class aClass) {
        classService.update(code, aClass);
        return Response.ok().entity("Successfully updated").build();
    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        classService.delete(code);
        return Response.ok().entity("Successfully deleted").build();
    }
}
