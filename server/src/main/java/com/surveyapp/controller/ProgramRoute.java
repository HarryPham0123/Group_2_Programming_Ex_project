package com.surveyapp.controller;
import com.surveyapp.model.Program;
import com.surveyapp.service.program.ProgramService;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/programs")
public class ProgramRoute {
    private ProgramService programService = new ProgramService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Program> getAll() {
        List<Program> programList = programService.getAll();
        return programList;
    }
}
