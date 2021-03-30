package com.surveyapp.routes;
import com.surveyapp.model.Class;
import com.surveyapp.model.Semester;
import com.surveyapp.service.ClassService;
import com.surveyapp.service.SemesterService;
import java.util.List;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/classes")
public class ClassRoute {
    private ClassService classService = new ClassService();
    @GET
    //@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAll() {
        List<Class> classList = classService.getAll();

        JsonArrayBuilder entryListModel = Json.createArrayBuilder();

        for(int i = 0; i < classList.size(); i++) {
            Class aClass = classList.get(i);
            JsonObject entry = Json.createObjectBuilder()
                    .add("Ccode", aClass.getCode())
                    .add("size", aClass.getSize())
                    .add("Scode", aClass.getSemester().getCode())
                    .add("AYCode", aClass.getSemester().getAcademicYear().getAYcode())
                    .add("Mcode", aClass.getModule().getCode())
                    .add("Mname", aClass.getModule().getName())
                    .build();
            entryListModel.add(entry);
        }

        JsonArray entryList = entryListModel.build();

        return Response.status(Response.Status.OK).entity(entryList.toString()).build();
    }

    @GET
    @Path("/{code}")
    public Response getByCode(@PathParam("code") String code) {
        Class aClass = classService.get(code);
        JsonObject entry = Json.createObjectBuilder()
                .add("Ccode", aClass.getCode())
                .add("size", aClass.getSize())
                .add("Scode", aClass.getSemester().getCode())
                .add("AYCode", aClass.getSemester().getAcademicYear().getAYcode())
                .add("Mcode", aClass.getModule().getCode())
                .add("Mname", aClass.getModule().getName())
                .build();

        return Response.status(Response.Status.OK).entity(entry.toString()).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String insert(Semester semester) {
        return "Underconstruction!";
    }
}