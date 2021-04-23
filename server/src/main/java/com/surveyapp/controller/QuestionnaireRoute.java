package com.surveyapp.controller;
import com.surveyapp.model.Code;
import com.surveyapp.model.Questionnaire;
import com.surveyapp.service.procedure.ProcedureService;

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
            @DefaultValue("null") @QueryParam("academic_year") String academic_year,
            @DefaultValue("null") @QueryParam("semester") String semester,
            @DefaultValue("null") @QueryParam("faculty") String faculty,
            @DefaultValue("null") @QueryParam("program") String program,
            @DefaultValue("null") @QueryParam("module") String module,
            @DefaultValue("null") @QueryParam("class") String clazz,
            @DefaultValue("null") @QueryParam("lecturer") String lecturer
    ) {
        try {
            Code code = new Code(academic_year, semester, faculty, program, module, lecturer, clazz);
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
            procedureService.insertQuestionnaire(questionnaire);
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", "successfully insert to database");
            return Response
                    .status(Response.Status.CREATED)
                    .entity(jsonObjectBuilder.build()).build();
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            exception.printStackTrace();
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(jsonObjectBuilder.build()).build();
        }
    }

    @GET
    @Path("/attendance_question")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSummaryAttendance(
            @DefaultValue("null") @QueryParam("academic_year") String academic_year,
            @DefaultValue("null") @QueryParam("semester") String semester,
            @DefaultValue("null") @QueryParam("faculty") String faculty,
            @DefaultValue("null") @QueryParam("program") String program,
            @DefaultValue("null") @QueryParam("module") String module,
            @DefaultValue("null") @QueryParam("class") String clazz,
            @DefaultValue("null") @QueryParam("lecturer") String lecturer
    ) {
        try {
            Code code = new Code(academic_year, semester, faculty, program, module, lecturer, clazz);
            return Response.status(Response.Status.OK).entity(procedureService.getSummaryAttendance(code)).build();
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }
    }

    @GET
    @Path("/gender_question")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSummaryGender(
            @DefaultValue("null") @QueryParam("academic_year") String academic_year,
            @DefaultValue("null") @QueryParam("semester") String semester,
            @DefaultValue("null") @QueryParam("faculty") String faculty,
            @DefaultValue("null") @QueryParam("program") String program,
            @DefaultValue("null") @QueryParam("module") String module,
            @DefaultValue("null") @QueryParam("class") String clazz,
            @DefaultValue("null") @QueryParam("lecturer") String lecturer
    ) {
        try {
            Code code = new Code(academic_year, semester, faculty, program, module, lecturer, clazz);
            return Response.status(Response.Status.OK).entity(procedureService.getSummaryGender(code)).build();
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }
    }

    @GET
    @Path("/{questionNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSummaryQuestion(
            @PathParam("questionNumber") String questionNumber,
            @DefaultValue("null") @QueryParam("academic_year") String academic_year,
            @DefaultValue("null") @QueryParam("semester") String semester,
            @DefaultValue("null") @QueryParam("faculty") String faculty,
            @DefaultValue("null") @QueryParam("program") String program,
            @DefaultValue("null") @QueryParam("module") String module,
            @DefaultValue("null") @QueryParam("class") String clazz,
            @DefaultValue("null") @QueryParam("lecturer") String lecturer
    ) {
        try {
            Code code = new Code(academic_year, semester, faculty, program, module, lecturer, clazz);
            if (questionNumber.equals("question_18")) {
                return Response.status(Response.Status.OK).entity(procedureService.getSummaryComment(code)).build();
            }
                return Response.status(Response.Status.OK).entity(procedureService.getSummaryQuestion(code, questionNumber)).build();
        } catch (Exception exception) {
            exception.printStackTrace();
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }
    }
}
