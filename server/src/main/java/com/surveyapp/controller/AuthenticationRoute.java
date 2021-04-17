package com.surveyapp.controller;
import com.surveyapp.model.User;

import com.surveyapp.model.AcademicYear;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;


@Path("/authenticate")
public class AuthenticationRoute {

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response authenticate(User authUser){
        if(authUser.getUsername().equals("usernamevalid") && authUser.getPassword().equals("passwordvalid")){
            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
            String authToken = Jwts.builder()
                    .claim("role", "programCoordinator")
                    .signWith(key)
                    .compact();

            System.out.println(authToken);

            NewCookie cookie = new NewCookie("sqmvsa", authToken, "/", "", "auth_token", NewCookie.DEFAULT_MAX_AGE, false);

            return Response.ok("OK").cookie(cookie).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }


}


