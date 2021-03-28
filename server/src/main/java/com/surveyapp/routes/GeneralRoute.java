package com.surveyapp.routes;

import com.surveyapp.service.ProcedureService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
