package routes;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response getAll() {
        return Response
                .status(200)
                .entity(new ArrayList<>(USERS.values()))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}