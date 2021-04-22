package com.surveyapp.controller;


import com.surveyapp.model.AcademicYear;
import com.surveyapp.service.academic_year.AcademicYearService;


import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * Create HTTP GET, POST, DELETE method for AcadamicYear
 * @author Nguyen Dang Khoa, Pham Minh Huy, Phan Cong Huy, Tran Van Hung
 * @return Response for the front-end
 */


@Path("/academic_year")
public class AcademicYearRoute {
    private AcademicYearService academicYearService = new AcademicYearService();

    // GET method for showing all tuples in AcademicYear table in DB show in the front-end
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<AcademicYear> getAll() {
        List<AcademicYear> academicYearList = new ArrayList<>();
        try{
            academicYearList = academicYearService.getAll();
            return academicYearList;
        }catch (Exception exception){
            System.out.println(exception);
        }
        return academicYearList;
    }

    //GET method for searching function (optional)
    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AcademicYear getByCode(@PathParam("code") String code) {
        AcademicYear academicYear = new AcademicYear();
        try{
            academicYear = academicYearService.get(code);
            return academicYear;
        }catch(Exception exception){
            System.out.println(exception);
        }
        return academicYear;
    }

    //PUT not needed

    //POST method for inserting new tuple into the AcademicYear table in DB show in the front-end
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(AcademicYear academicYear) {
        try {
            academicYearService.save(academicYear);
            return Response.status(Response.Status.OK).build();
        } catch (Exception exception) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    //DELETE method for deleting a tuple from the AcademicYear table in DB show in the front-end
    @Path("/{code}")
    @DELETE
    public Response delete(@PathParam("code") String code) {
        try{
            academicYearService.delete(code);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

}
