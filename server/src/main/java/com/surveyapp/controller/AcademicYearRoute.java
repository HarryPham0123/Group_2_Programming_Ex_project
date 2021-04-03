package com.surveyapp.controller;

import com.surveyapp.model.AcademicYear;
import com.surveyapp.model.Lecturer;
import com.surveyapp.service.AcademicYearService;


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

}
