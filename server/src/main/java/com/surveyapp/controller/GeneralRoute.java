package com.surveyapp.controller;

import com.surveyapp.model.Code;
import com.surveyapp.service.procedure.ProcedureService;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/general")
public class GeneralRoute {
    private ProcedureService procedureService = new ProcedureService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGeneral(
            @DefaultValue("null") @QueryParam("academic_year") String academic_year,
            @DefaultValue("null") @QueryParam("semester") String semester,
            @DefaultValue("null") @QueryParam("faculty") String faculty,
            @DefaultValue("null") @QueryParam("program") String program,
            @DefaultValue("null") @QueryParam("module") String module,
            @DefaultValue("null") @QueryParam("class") String clazz,
            @DefaultValue("null") @QueryParam("lecturer") String lecturer
    ) {
        try {
            System.out.println("Academic year from route layer is null ? " + (academic_year == null));
            Code code = new Code(academic_year, semester, faculty, program, module, clazz, lecturer);
            return Response
                    .status(Response.Status.OK)
                    .entity(procedureService.getAll(code))
                    .build();
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            exception.printStackTrace();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }
    }

    /*
    *     @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCode(
            @QueryParam("academic_year") String academic_year,
            @QueryParam("semester") String semester,
            @QueryParam("faculty") String faculty,
            @QueryParam("program") String program,
            @QueryParam("module") String module,
            @QueryParam("class") String clazz,
            @QueryParam("lecturer") String lecturer
    ) {
      try {
          String fetchedCode = procedureService.getCode(
                  academic_year,
                  semester,
                  faculty,
                  program,
                  module,
                  clazz,
                  lecturer
          );
          return Response.status(200).entity(fetchedCode).build();
      } catch (Exception exception) {
          JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
          jsonObjectBuilder.add("message", exception.getMessage());
          return Response.status(400).entity(jsonObjectBuilder.build()).build();
      }
    }
    * */

}
