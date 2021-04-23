package com.surveyapp.controller;

import com.surveyapp.model.AYFacultyProgramModule;
import com.surveyapp.service.ay_faculty_program_module.AYFacultyProgramModuleService;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/academic_year_faculty_program_module")
public class AYFacultyProgramModuleRoute {
    private AYFacultyProgramModuleService ayFacultyProgramModuleService = new AYFacultyProgramModuleService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<AYFacultyProgramModule> getAll() {
        List<AYFacultyProgramModule> ayFacultyProgramModuleList = ayFacultyProgramModuleService.getAll();
        return ayFacultyProgramModuleList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AYFacultyProgramModule getByCode(@PathParam("code") String academicYearCode) {
        AYFacultyProgramModule ayFacultyProgramModule = ayFacultyProgramModuleService.get(academicYearCode);
        return ayFacultyProgramModule;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(AYFacultyProgramModule ayFacultyProgramModule) {
        try {
            String messageFromOperation = ayFacultyProgramModuleService.save(ayFacultyProgramModule);
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
                           @QueryParam("program_code") String programCode,
                           @QueryParam("module_code") String moduleCode)
    {
        AYFacultyProgramModule ayFacultyProgramModule = new AYFacultyProgramModule(academicYearCode, facultyCode, programCode, moduleCode);
        try {
            String messageFromOperation = ayFacultyProgramModuleService.delete(ayFacultyProgramModule);
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
