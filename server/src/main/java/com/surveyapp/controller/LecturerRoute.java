package com.surveyapp.controller;
import com.surveyapp.model.Lecturer;
import com.surveyapp.service.lecturer.LecturerDAO;
import com.surveyapp.service.lecturer.LecturerService;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/lecturers")
public class LecturerRoute {
    private LecturerService lecturerService = new LecturerService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Lecturer> getAll() throws Exception{
        List<Lecturer> lecturerList = new ArrayList<>();
        try{
            lecturerList = lecturerService.getAll();
            return lecturerList;
        }catch(Exception exception){
            System.out.println(exception);
        }
        return lecturerList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Lecturer getByCode(@PathParam("code") String code) throws Exception {
        Lecturer lecturer = new Lecturer();
        try{
            lecturer = lecturerService.get(code);
            return lecturer;
        }catch(Exception exception){
            System.out.println(exception);
        }
        return lecturer;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(Lecturer lecturer) throws Exception {
        try{
            lecturerService.save(lecturer);
            return Response.status(Response.Status.OK).build();
        }catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Lecturer lecturer) throws Exception {
        try{
            lecturerService.update(code, lecturer);
            return Response.status(Response.Status.OK).build();
        }catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) throws Exception {
        try{
            lecturerService.delete(code);
            return Response.status(Response.Status.OK).build();
        }catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }
}