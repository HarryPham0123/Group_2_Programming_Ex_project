package com.surveyapp.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.surveyapp.model.Faculty;
import com.surveyapp.service.faculty.FacultyService;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
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
        if(!facultyService.save(faculty)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();

    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Faculty faculty) {
        if(!facultyService.update(code, faculty)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();

    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        if(!facultyService.delete(code)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();

    }

}

