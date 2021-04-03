package com.surveyapp.controller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.surveyapp.model.Lecturer;
import com.surveyapp.service.LecturerService;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@JsonIgnoreProperties(ignoreUnknown = true)
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
        lecturerService.save(lecturer);
        return Response.ok().entity("New lecturer successfully inserted").build();
    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Lecturer lecturer) {
        lecturerService.update(code, lecturer);
        return Response.ok().entity("Successfully updated").build();
    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        lecturerService.delete(code);
        return Response.ok().entity("Successfully deleted").build();
    }
}
