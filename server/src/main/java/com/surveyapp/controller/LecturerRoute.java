package com.surveyapp.routes;
import com.surveyapp.model.Lecturer;
import com.surveyapp.service.LecturerService;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/lecturers")
public class LecturerRoute {
    private LecturerService lecturerService = new LecturerService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Lecturer> getAll() {
        List<Lecturer> lecturerList = lecturerService.getAll();
        return lecturerList;
    }

    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Lecturer getByCode(@PathParam("code") String code) {
        Lecturer lecturer = lecturerService.get(code);
        return lecturer;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String insert(Lecturer lecturer) {
        return "Underconstruction!";
    }
}