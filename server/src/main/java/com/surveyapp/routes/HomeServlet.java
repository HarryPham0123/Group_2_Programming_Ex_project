package com.surveyapp.routes;
import com.surveyapp.Configuration;
import com.surveyapp.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/home")
public class HomeServlet {
    private static final Map<Integer, User> USERS = new HashMap<>();
    static {
        // Create dummy data
        for (int i = 1; i <= 10; i++) { USERS.put(i, new User(i, "dummy " + i)); } }
        private int generateUniqueId() { return USERS.keySet().stream().max((x1, x2) -> x1 - x2).orElse(0) + 1;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<User> getAll() {
        try {
            Connection connection = Configuration.getAcademiaConnection();
            if (connection != null) {
                Statement stm = connection.createStatement();
                ResultSet res = stm.executeQuery("select * from lecturer");
                while(res.next()) {
                    for (int i = 1; i <= res.getMetaData().getColumnCount(); i++) {
                        System.out.println(res.getObject(i));
                    }
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(USERS.values());
    }
}