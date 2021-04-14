package com.surveyapp.controller;


import com.surveyapp.model.AcademicYear;
import com.surveyapp.service.academic_year.AcademicYearService;


import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/academic_year")
public class AcademicYearRoute {
    private AcademicYearService academicYearService = new AcademicYearService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<AcademicYear> getAll() {
        List<AcademicYear> academicYearList = academicYearService.getAll();
        return academicYearList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AcademicYear getByCode(@PathParam("code") String code) {
        AcademicYear academicYear = academicYearService.get(code);
        return academicYear;
    }

    //PUT not needed

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(AcademicYear academicYear) {
        try {
            academicYearService.save(academicYear);
            return Response.ok().entity("New academic year successfully inserted").build();
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }

    }

    @Path("/{code}")
    @DELETE
    public Response delete(@PathParam("code") String code) {
        try {
            academicYearService.delete(code);
            return Response.ok().entity("Successfully deleted").build();
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }

    }

}
