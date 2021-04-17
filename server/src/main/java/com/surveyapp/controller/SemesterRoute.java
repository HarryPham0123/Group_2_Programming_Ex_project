package com.surveyapp.controller;
import com.surveyapp.model.Semester;
import com.surveyapp.service.semester.SemesterService;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/semesters")
public class SemesterRoute {
    private SemesterService semesterService = new SemesterService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Semester> getAll() throws Exception {
        List<Semester> semesterList = new ArrayList<>();
        try{
            semesterList = semesterService.getAll();
            return semesterList;
        }catch (Exception exception){
            System.out.println(exception);
        }
        return semesterList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Semester getByCode(@PathParam("code") String code)throws Exception {
        Semester semester = new Semester();
        try{
            semester = semesterService.get(code);
            return semester;
        }catch (Exception exception){
            System.out.println(exception);
        }
        return semester;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(Semester semester)throws Exception {
        try{
            semesterService.save(semester);
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
    public Response update(@PathParam("code") String code, Semester semester) throws Exception{
        try{
            semesterService.update(code, semester);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) throws Exception{
        try{
            semesterService.delete(code);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }
}
