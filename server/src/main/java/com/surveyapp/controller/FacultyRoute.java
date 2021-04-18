package com.surveyapp.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.surveyapp.model.Faculty;
import com.surveyapp.service.faculty.FacultyService;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Path("/faculties")
public class FacultyRoute {
    private FacultyService facultyService = new FacultyService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Faculty> getAll() {
        List<Faculty> facultyList = new ArrayList<>();
        try{
            facultyList = facultyService.getAll();
            return facultyList;
        }catch(Exception exception){
            System.out.println(exception);
        }
        return facultyList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Faculty getByCode(@PathParam("code") String code) {
        Faculty faculty = new Faculty();
        try{
            faculty = facultyService.get(code);
            return faculty;
        }catch(Exception exception){
            System.out.println(exception);
        }
        return faculty;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(Faculty faculty) {
        try{
            facultyService.save(faculty);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Faculty faculty){
        try{
            facultyService.update(code, faculty);
            return Response.status(Response.Status.OK).build();
        }catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        try{
            facultyService.delete(code);
            return Response.status(Response.Status.OK).build();
        }catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }
}

