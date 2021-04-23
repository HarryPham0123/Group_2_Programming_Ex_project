package com.surveyapp.controller;
import com.surveyapp.model.Class;
import com.surveyapp.model.Code;
import com.surveyapp.service.clazz.ClassService;
import com.surveyapp.service.procedure.GetSummaryCommentDAO;
import com.surveyapp.service.procedure.ProcedureService;

import java.util.List;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * Create HTTP GET, PUT, POST, DELETE method for Class
 * @author Tran Van Hung, Phan Cong Huy, Nguyen Dang Khoa
 * @return Response for the front-end
 */


@Path("/classes")
public class ClassRoute {
    private ClassService classService = new ClassService();
    private ProcedureService procedureService = new ProcedureService();

    // GET method for showing all tuples in Class table in DB show in the front-end
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Class> getAll()  {
        List<Class> classList = new ArrayList<>();
        try{
            classList = classService.getAll();
            return classList;
        }catch (Exception exception){
            System.out.println(exception);
        }
        return classList;
    }

    //GET method for searching function (optional)
    @GET
    @Path("/{code}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Class getByCode(@PathParam("code") String code) {
        Class aClass = new Class();
        try{
            aClass = classService.get(code);
            return aClass;
        } catch(Exception exception){
            System.out.println(exception);
        }
        return aClass;
    }

    //Get class's size base on academic codes
    @GET
    @Path("/size")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClassesSize(
            @DefaultValue("null") @QueryParam("academic_year") String academic_year,
            @DefaultValue("null") @QueryParam("semester") String semester,
            @DefaultValue("null") @QueryParam("faculty") String faculty,
            @DefaultValue("null") @QueryParam("program") String program,
            @DefaultValue("null") @QueryParam("module") String module,
            @DefaultValue("null") @QueryParam("class") String clazz,
            @DefaultValue("null") @QueryParam("lecturer") String lecturer
    ) {
        try {
            Code code = new Code(academic_year, semester, faculty, program, module, lecturer, clazz);
            return Response.status(Response.Status.OK).entity(procedureService.getTotalClassesSize(code)).build();
        } catch (Exception exception) {
            exception.printStackTrace();
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(jsonObjectBuilder.build()).build();
        }
    }

    //POST method for inserting new tuple into the Class table in DB show in the front-end
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insert(Class aClass) {
        try{
            classService.save(aClass);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    //PUT method for modifying a tuple in the Class table in DB show in the front-end
    @PUT
    @Path("/{code}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("code") String code, Class aClass) {
        try{
            classService.update(code, aClass);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }

    //DELETE method for deleting a tuple from the Class table in DB and show in the front-end
    @DELETE
    @Path("/{code}")
    public Response delete(@PathParam("code") String code) {
        try{
            classService.delete(code);
            return Response.status(Response.Status.OK).build();
        } catch(Exception exception){
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("message", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonObjectBuilder.build()).build();
        }
    }
}
