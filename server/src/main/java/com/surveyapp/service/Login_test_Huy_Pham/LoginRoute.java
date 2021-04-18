package com.surveyapp.service.Login_test_Huy_Pham;


import com.surveyapp.model.AcademicYear;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.ws.rs.*;
import javax.ws.rs.core.NewCookie;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.naming.NamingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/Huytest")
public class LoginRoute {
    private LoginService loginService= new LoginService();



    public Optional<LoginModel> verify(String username, String password) {
        Optional<LoginModel> data = loginService.verify(username,password);

        return data;
    }
    @POST
    @Path("/{username}/{password}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response verif(@PathParam("username")String username, @PathParam("password") String password) {
        Optional<LoginModel> data  = verify(username,password);
        System.out.println("Confirmed data packet, user validity is "+data.get().getValid());
        if (data.get().getValid().equals("Yes")){
            User user = new User(username,password,data);
            String token = JWTService.createJWT(user);
            NewCookie cookie = new NewCookie("sqmvsa", token, "/", "", "token", NewCookie.DEFAULT_MAX_AGE, false);

            return Response.ok("OK").cookie(cookie).build();

        }
        //
        else {
            return Response.status(Response.Status.FORBIDDEN).entity("Wrong username or password").build();
        }
    }
//UNDER CONSTRUCTION LMAO
    @GET
    @Path("/{token}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response verif(@PathParam("token")String jwt) {
        Boolean info = JWTService.isTokenExpired(jwt);

        if (info){

            return Response.ok().build();
        }
        //
        else {
            return Response.status(Response.Status.FORBIDDEN).entity("Wrong token used").build();
        }
    }

}
