package com.surveyapp.controller;
import com.surveyapp.model.Semester;
import com.surveyapp.service.semester.SemesterService;
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
    public Response insert(Semester semester) {
        if(!semesterService.save(semester)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();


    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Semester semester) {
        if(!semesterService.update(code, semester)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();


    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        if(!semesterService.delete(code)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();


    }
}
