package com.surveyapp.routes;
import com.surveyapp.model.Lecturer;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/lecturers")
public class LecturerRoute {
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Lecturer> getAll() throws Exception{
        List<Lecturer> lecturerList = new ArrayList<>();
        try {
            Connection connection = new DBUtil().getConnection();
            if (connection != null) {
                Statement stm = connection.createStatement();
                ResultSet res = stm.executeQuery("select * from lecturer");
                lecturerList = (ArrayList) ObjMapper.toObject(Lecturer.class, res);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return lecturerList;
    }
}