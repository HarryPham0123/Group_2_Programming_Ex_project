package com.surveyapp.controller;

import com.surveyapp.model.Faculty;
import com.surveyapp.model.Module;
import com.surveyapp.service.module.ModuleService;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
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
    public Response insert(Module module) {
        try {
            moduleService.save(module);
            return Response.ok().entity("New faculty successfully inserted").build();
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }

    }

    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Module module) {
        try {
            moduleService.update(code, module);
            return Response.ok().entity("Successfully updated").build();
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }

    }

    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        try {
            moduleService.delete(code);
            return Response.ok().entity("Successfully deleted").build();
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }

    }
}
