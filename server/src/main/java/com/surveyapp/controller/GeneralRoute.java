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

}
