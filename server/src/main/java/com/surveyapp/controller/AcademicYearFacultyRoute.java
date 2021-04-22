package com.surveyapp.controller;

import com.surveyapp.model.AcademicYearFaculty;
import com.surveyapp.service.academic_year_faculty.AcademicYearFacultyService;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/academic_year_faculty")
public class AcademicYearFacultyRoute {
    private AcademicYearFacultyService academicYearFacultyService = new AcademicYearFacultyService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<AcademicYearFaculty> getAll() {
        List<AcademicYearFaculty> academicYearFacultyList = academicYearFacultyService.getAll();
        return academicYearFacultyList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AcademicYearFaculty getByCode(@PathParam("code") String academicYearCode) {
        AcademicYearFaculty academicYearFaculty = academicYearFacultyService.get(academicYearCode);
        return academicYearFaculty;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(AcademicYearFaculty academicYearFaculty) {
        try {
            String messageFromOperation = academicYearFacultyService.save(academicYearFaculty);
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
    public Response delete(@QueryParam("academic_year") String academicYearCode,
                           @QueryParam("faculty_code") String facultyCode)
    {
        AcademicYearFaculty academicYearFaculty = new AcademicYearFaculty(academicYearCode, facultyCode);
        try {
            String messageFromOperation = academicYearFacultyService.delete(academicYearFaculty);
            if(messageFromOperation.equals("Success")) {
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(messageFromOperation).build();
            }
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }
}
