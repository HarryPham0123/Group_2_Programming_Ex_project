package com.surveyapp.controller;

import com.surveyapp.model.AcademicYear;
import com.surveyapp.model.Lecturer;
import com.surveyapp.service.AcademicYearService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/acy")
public class AcademicYearRoute {
    private AcademicYearService academicYearService = new AcademicYearService();
    @GET //GET all acy instances
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<AcademicYear> getAll() {
        List<AcademicYear> academicYearList = academicYearService.getAll();
        return academicYearList;
    }

    @GET //this is get a specific acy with provided code
    @Path("/acy/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AcademicYear getByCode(@PathParam("code") String code) {
        AcademicYear academicYear = academicYearService.get(code);
        return academicYear;
    }

    @POST
    @Path("/acy/in/")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(AcademicYear academicYear) {
        academicYearService.save(academicYear);
        return Response.ok().entity("New ACY insert operation success").build();
    }

    @DELETE
    @Path("/acy/del/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response delete(AcademicYear academicYear){
        academicYearService.delete(academicYear);
        return Response.ok().entity("ACY delete operation carried out").build();
    }
}
