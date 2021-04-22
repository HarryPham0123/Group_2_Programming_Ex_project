package com.surveyapp.controller;

import com.surveyapp.model.AYFacultyProgram;
import com.surveyapp.service.ay_faculty_program.AYFacultyProgramService;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/academic_year_faculty_program")
public class AYFacultyProgramRoute {
    private AYFacultyProgramService ayFacultyProgramService = new AYFacultyProgramService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<AYFacultyProgram> getAll() {
        List<AYFacultyProgram> ayFacultyProgramList = ayFacultyProgramService.getAll();
        return ayFacultyProgramList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AYFacultyProgram getByCode(@PathParam("code") String academicYearCode) {
        AYFacultyProgram ayFacultyProgram = ayFacultyProgramService.get(academicYearCode);
        return ayFacultyProgram;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(AYFacultyProgram ayFacultyProgram) {
        try {
            String messageFromOperation = ayFacultyProgramService.save(ayFacultyProgram);
            if(messageFromOperation.equals("Success")) {
                return Response.status(Response.Status.OK).entity(messageFromOperation).build();
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
                           @QueryParam("faculty_code") String facultyCode,
                           @QueryParam("program_code") String programCode)
    {
        AYFacultyProgram ayFacultyProgram = new AYFacultyProgram(academicYearCode, facultyCode, programCode);
        try {
            String messageFromOperation = ayFacultyProgramService.delete(ayFacultyProgram);
            if(messageFromOperation.equals("Success")) {
                return Response.status(Response.Status.OK).entity(messageFromOperation).build();
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
