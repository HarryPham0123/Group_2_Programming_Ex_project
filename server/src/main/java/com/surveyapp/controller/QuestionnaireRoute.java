package com.surveyapp.controller;

import com.sun.jdi.event.ExceptionEvent;
import com.surveyapp.model.Questionnaire;
import com.surveyapp.service.ProcedureService;
import com.surveyapp.util.ObjectConverter;

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
            return Response.status(200).entity(
                    procedureService.getAnswer(
                            academic_year,
                            semester,
                            faculty,
                            program,
                            module,
                            lecturer,
                            clazz
                    )
            ).build();
        } catch(Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(400).entity(jsonObjectBuilder.build()).build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertQuestionnaire(Questionnaire questionnaire) {
        System.out.println(ObjectConverter.toJSON(questionnaire.getAnswer()));
        try {
            return Response.status(200).entity(
                    procedureService.insertAnswer(
                            questionnaire.getCcode(),
                            questionnaire.getLcode(),
                            ObjectConverter.toJSON(questionnaire.getAnswer())
                    )
            ).build();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
