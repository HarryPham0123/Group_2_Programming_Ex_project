package com.surveyapp.controller;
import com.surveyapp.model.Program;
import com.surveyapp.service.program.ProgramService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/programs")
public class ProgramRoute {
    private ProgramService programService = new ProgramService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Program> getAll() {
        List<Program> programList = programService.getAll();
        return programList;
    }
    
     @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Program getByCode(@PathParam("code") String code) {
        Program program = programService.get(code);
        return program;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(Program program) {
        programService.save(program);
        return Response.ok().entity("New program successfully inserted").build();
    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Program program) {
        programService.update(code, program);
        return Response.ok().entity("Successfully updated").build();
    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        programService.delete(code);
        return Response.ok().entity("Successfully deleted").build();
    }
}