package com.surveyapp.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.surveyapp.model.Faculty;
import com.surveyapp.service.FacultyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Path("/faculties")
public class FacultyRoute {
    private FacultyService facultyService = new FacultyService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Faculty> getAll(){
        List<Faculty> facultyList = facultyService.getAll();
        return facultyList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Faculty getByCode(@PathParam("code") String code) {
        Faculty faculty = facultyService.get(code);
        return faculty;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(Faculty faculty) {
        facultyService.save(faculty);
        return Response.ok().entity("New faculty successfully inserted").build();
    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Faculty faculty) {
        facultyService.update(code, faculty);
        return Response.ok().entity("Successfully updated").build();
    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        facultyService.delete(code);
        return Response.ok().entity("Successfully deleted").build();
    }

}

