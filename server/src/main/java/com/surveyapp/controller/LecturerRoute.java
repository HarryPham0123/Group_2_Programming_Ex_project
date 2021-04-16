package com.surveyapp.controller;
import com.surveyapp.model.Lecturer;
import com.surveyapp.service.lecturer.LecturerService;
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
    public List<Lecturer> getAll() {
        List<Lecturer> lecturerList = lecturerService.getAll();
        return lecturerList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Lecturer getByCode(@PathParam("code") String code) {
        Lecturer lecturer = lecturerService.get(code);
        return lecturer;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(Lecturer lecturer) {
        if(!lecturerService.save(lecturer)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();

    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Lecturer lecturer) {
        if(!lecturerService.update(code, lecturer)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();

    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        if(!lecturerService.delete(code)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();

    }
}