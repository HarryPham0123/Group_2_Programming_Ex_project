package com.surveyapp.controller;
import com.surveyapp.model.Semester;
import com.surveyapp.service.semester.SemesterService;
import java.util.List;

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
        semesterService.save(semester);
        return Response.ok().entity("New semester successfully inserted").build();
    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Semester semester) {
        semesterService.update(code, semester);
        return Response.ok().entity("Successfully updated").build();
    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        semesterService.delete(code);
        return Response.ok().entity("Successfully deleted").build();
    } 
}
