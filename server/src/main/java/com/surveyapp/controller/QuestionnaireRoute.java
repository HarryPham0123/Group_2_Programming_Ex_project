package com.surveyapp.controller;

import com.surveyapp.model.Code;
import com.surveyapp.model.Questionnaire;
import com.surveyapp.service.ProcedureService;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/questionnaire")
public class QuestionnaireRoute {
    private ProcedureService procedureService = new ProcedureService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestionnaire(
            @QueryParam("academic_year") String academic_year,
            @QueryParam("semester") String semester,
            @QueryParam("faculty") String faculty,
            @QueryParam("program") String program,
            @QueryParam("module") String module,
            @QueryParam("class") String clazz,
            @QueryParam("lecturer") String lecturer
    ) {
        try {
            Code code = new Code(academic_year, semester, faculty, program, module, clazz, lecturer);
            return Response.status(Response.Status.OK).entity(procedureService.getQuestionnaire(code)).build();
        } catch(Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertQuestionnaire(Questionnaire questionnaire) {
        try {
            return Response.status(200).entity(procedureService.insertQuestionnaire(questionnaire)).build();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
