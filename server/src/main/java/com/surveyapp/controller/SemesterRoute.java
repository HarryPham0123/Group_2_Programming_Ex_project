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

/**
 *
 * Create HTTP GET, PUT, POST, DELETE method for Module
 * @author Tran Van Hung, Nguyen Dang Khoa, Phan Cong Huy
 *@return Response for the front-end
 */

@Path("/semesters")
public class SemesterRoute {
    private SemesterService semesterService = new SemesterService();

    // GET method for showing all tuples in Semester table in DB and show in the front-end
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Semester> getAll() {
        List<Semester> semesterList = new ArrayList<>();
        try{
            semesterList = semesterService.getAll();
            return semesterList;
        }catch (Exception exception){
            System.out.println(exception);
        }
        return semesterList;
    }

    //GET method for searching function (optional)
    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Semester getByCode(@PathParam("code") String code){
        Semester semester = new Semester();
        try{
            semester = semesterService.get(code);
            return semester;
        }catch (Exception exception){
            System.out.println(exception);
        }
        return semester;
    }

    //POST method for inserting new tuple into the Faculty table in DB and show in the front-end
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(Semester semester){
        try{
            semesterService.save(semester);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    //PUT method for modifying a tuple in the Semester table in DB and show in the front-end
    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Semester semester) {
        try{
            semesterService.update(code, semester);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    //DELETE method for deleting a tuple from the Semester table in DB and show in the front-end
    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
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
