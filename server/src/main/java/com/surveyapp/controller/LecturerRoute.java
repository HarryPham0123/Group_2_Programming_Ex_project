package com.surveyapp.controller;
import com.surveyapp.model.Lecturer;
import com.surveyapp.service.lecturer.LecturerDAO;
import com.surveyapp.service.lecturer.LecturerService;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * Create HTTP GET, PUT, POST, DELETE method for Faculty
 * @author Tran Van Hung, Nguyen Dang Khoa, Phan Cong Huy, Pham Minh Huy
 *@return Response for the front-end
 */

@Path("/lecturers")
public class LecturerRoute {
    private LecturerService lecturerService = new LecturerService();

    // GET method for showing all tuples in Lecturer table in DB show in the front-end
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Lecturer> getAll() {
        List<Lecturer> lecturerList = new ArrayList<>();
        try{
            lecturerList = lecturerService.getAll();
            return lecturerList;
        }catch(Exception exception){
            System.out.println(exception);
        }
        return lecturerList;
    }

    //GET method for searching function (optional)
    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Lecturer getByCode(@PathParam("code") String code) {
        Lecturer lecturer = new Lecturer();
        try{
            lecturer = lecturerService.get(code);
            return lecturer;
        }catch(Exception exception){
            System.out.println(exception);
        }
        return lecturer;
    }

    //POST method for inserting new tuple into the Lecturer table in DB show in the front-end
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(Lecturer lecturer) {
        try{
            lecturerService.save(lecturer);
            return Response.status(Response.Status.OK).build();
        }catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    //PUT method for modifying a tuple in the Lecturer table in DB show in the front-end
    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Lecturer lecturer){
        try{
            lecturerService.update(code, lecturer);
            return Response.status(Response.Status.OK).build();
        }catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    //DELETE method for deleting a tuple from the Lecturer table in DB and show in the front-end
    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        try{
            lecturerService.delete(code);
            return Response.status(Response.Status.OK).build();
        }catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }
}