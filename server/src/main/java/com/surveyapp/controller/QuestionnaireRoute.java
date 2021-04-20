package com.surveyapp.controller;
import com.surveyapp.model.Code;
import com.surveyapp.model.Questionnaire;
import com.surveyapp.service.procedure.ProcedureService;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * Create HTTP GET, POST method to get answers from questionnaire
 * @author Phan Cong Huy, Nguyen Dang Khoa
 * @return Response for the front-end
 *
 */

@Path("/questionnaire")
public class QuestionnaireRoute {
    private ProcedureService procedureService = new ProcedureService();

    //GET method for getting questionnaire
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
            Code code = new Code(academic_year, semester, faculty, program, module, lecturer, clazz);
            return Response.status(Response.Status.OK).entity(procedureService.getQuestionnaire(code)).build();
        } catch(Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }
    }

    //POST method for inserting questionnaire to DB
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

    //GET method for getting all attendance answers
    @GET
    @Path("/attendance_question")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSummaryAttendance(
            @QueryParam("academic_year") String academic_year,
            @QueryParam("semester") String semester,
            @QueryParam("faculty") String faculty,
            @QueryParam("program") String program,
            @QueryParam("module") String module,
            @QueryParam("class") String clazz,
            @QueryParam("lecturer") String lecturer
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

    //GET method for getting all gender answers
    @GET
    @Path("/gender_question")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSummaryGender(
            @QueryParam("academic_year") String academic_year,
            @QueryParam("semester") String semester,
            @QueryParam("faculty") String faculty,
            @QueryParam("program") String program,
            @QueryParam("module") String module,
            @QueryParam("class") String clazz,
            @QueryParam("lecturer") String lecturer
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

    //GET method for getting all other answers
    @GET
    @Path("/{questionNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSummaryQuestion(
            @PathParam("questionNumber") String questionNumber,
            @QueryParam("academic_year") String academic_year,
            @QueryParam("semester") String semester,
            @QueryParam("faculty") String faculty,
            @QueryParam("program") String program,
            @QueryParam("module") String module,
            @QueryParam("class") String clazz,
            @QueryParam("lecturer") String lecturer
    ) {
        try {
            Code code = new Code(academic_year, semester, faculty, program, module, lecturer, clazz);
            return Response.status(Response.Status.OK).entity(procedureService.getSummaryQuestion(code, questionNumber  )).build();
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }
    }
}
