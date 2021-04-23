package com.surveyapp.controller;

import com.surveyapp.model.LecturerClass;
import com.surveyapp.service.lecturer_class.LecturerClassService;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/lecturer_class")
public class LecturerClassRoute {
    private LecturerClassService lecturerClassService = new LecturerClassService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<LecturerClass> getAll() {
        List<LecturerClass> lecturerClassList = lecturerClassService.getAll();
        return lecturerClassList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public LecturerClass getByCode(@PathParam("code") String code) {
        LecturerClass lecturerClass = lecturerClassService.get(code);
        return lecturerClass;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(LecturerClass lecturerClass) {
        try {
            String messageFromOperation = lecturerClassService.save(lecturerClass);
            if(messageFromOperation.equals("Success")) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(messageFromOperation).build();
            }
        } catch(Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    @DELETE
    public Response delete(@QueryParam("class_code") String classCode,
                           @QueryParam("lecturer_code") String lecturerCode)
    {
        LecturerClass lecturerClass = new LecturerClass(lecturerCode, classCode);
        try {
            String messageFromOperation = lecturerClassService.delete(lecturerClass);
            if(messageFromOperation.equals("Success")) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(messageFromOperation).build();
            }

        } catch(Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }

    }
}
