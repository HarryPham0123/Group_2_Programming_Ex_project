package com.surveyapp.routes;

import com.surveyapp.model.Lecturer;
import com.surveyapp.model.Module;
import com.surveyapp.service.LecturerService;
import com.surveyapp.service.ModuleService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
}
