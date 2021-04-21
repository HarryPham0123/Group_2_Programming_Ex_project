package com.surveyapp.controller;
import com.surveyapp.model.Program;
import com.surveyapp.service.program.ProgramService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
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
        if(!programService.save(program)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();

    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Program program) {
        if(!programService.update(code, program)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();

    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        if(!programService.delete(code)) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
        return Response.status(Response.Status.OK).build();

    }
}
