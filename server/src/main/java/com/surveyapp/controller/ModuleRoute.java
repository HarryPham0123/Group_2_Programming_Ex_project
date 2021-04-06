package com.surveyapp.controller;

import com.surveyapp.model.Faculty;
import com.surveyapp.model.Module;
import com.surveyapp.service.module.ModuleService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("/module")
public class ModuleRoute {
    private ModuleService moduleService = new ModuleService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Module> getAll() {
        List<Module> moduleList = moduleService.getAll();
        return moduleList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Module getByCode(@PathParam("code") String code) {
        Module module = moduleService.get(code);
        return module;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(Faculty faculty) {
        moduleService.save(faculty);
        return Response.ok().entity("New faculty successfully inserted").build();
    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Faculty faculty) {
        moduleService.update(code, faculty);
        return Response.ok().entity("Successfully updated").build();
    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        moduleService.delete(code);
        return Response.ok().entity("Successfully deleted").build();
    }
}
