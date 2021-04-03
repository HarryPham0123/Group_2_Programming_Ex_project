package com.surveyapp.controller;

import com.surveyapp.service.ProcedureService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/general")
public class GeneralRoute {
    private ProcedureService procedureService = new ProcedureService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGeneral() {
        return Response.status(Response.Status.OK).entity(procedureService.getAll()).build();
    }

    @GET
    @Path("/acy/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAcademicYearGeneral(@PathParam("code") String code) {
        return Response.status(Response.Status.OK).entity(procedureService.getAcademicYear(code)).build();
    }

    @GET
    @Path("/semester/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSemesterGeneral(@PathParam("code") String code) {
        return Response.status(Response.Status.OK).entity(procedureService.getSemester(code)).build();
    }

    @GET
    @Path("/faculty/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFacultyGeneral(@PathParam("code") String code) {
        return Response.status(Response.Status.OK).entity(procedureService.getFaculty(code)).build();
    }

    @GET
    @Path("/program/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProgramGeneral(@PathParam("code") String code) {
        return Response.status(Response.Status.OK).entity(procedureService.getProgram(code)).build();
    }

    @GET
    @Path("/module/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleGeneral(@PathParam("code") String code) {
        return Response.status(Response.Status.OK).entity(procedureService.getModule(code)).build();
    }

    @GET
    @Path("/lecturer/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLecturerGeneral(@PathParam("code") String code) {
        return Response.status(Response.Status.OK).entity(procedureService.getLecturer(code)).build();
    }

    @GET
    @Path("/class/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClassGeneral(@PathParam("code") String code) {
        return Response.status(Response.Status.OK).entity(procedureService.getClass(code)).build();
    }
}
