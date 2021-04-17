package com.surveyapp.controller;
import com.surveyapp.model.Program;
import com.surveyapp.service.program.ProgramService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
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
    public List<Program> getAll() throws Exception{
        List<Program> programList = new ArrayList<>();
        try{
            programList = programService.getAll();
            return programList;
        }catch (Exception exception){
            System.out.println(exception);
        }
        return programList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Program getByCode(@PathParam("code") String code) throws Exception{
        Program program = new Program();
        try{
            program = programService.get(code);
            return program;
        }catch (Exception exception){
            System.out.println(exception);
        }
        return program;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(Program program)throws Exception {
        try{
            programService.save(program);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Program program) throws Exception{
        try{
            programService.update(code, program);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code)throws Exception {
        try{
            programService.delete(code);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }
}
