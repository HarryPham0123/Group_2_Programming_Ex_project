package com.surveyapp.routes;
import com.surveyapp.model.Semester;
import com.surveyapp.service.SemesterService;
import java.util.List;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/semesters")
public class SemesterRoute {
    private SemesterService semesterService = new SemesterService();
    @GET
    //@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAll() {
        List<Semester> semesterList = semesterService.getAll();

        JsonArrayBuilder entryListModel = Json.createArrayBuilder();

        for(int i = 0; i < semesterList.size(); i++) {
            Semester semester = semesterList.get(i);
            JsonObject entry = Json.createObjectBuilder()
                    .add("Scode", semester.getCode())
                    .add("AYcode", semester.getAcademicYear().getAYcode())
                    .build();
            entryListModel.add(entry);
        }

        JsonArray entryList = entryListModel.build();

        return Response.status(Response.Status.OK).entity(entryList.toString()).build();
    }

    @GET
    @Path("/{code}")
    public Response getByCode(@PathParam("code") String code) {
        Semester semester = semesterService.get(code);
        JsonObject entry = Json.createObjectBuilder()
                .add("Scode", semester.getCode())
                .add("AYcode", semester.getAcademicYear().getAYcode())
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