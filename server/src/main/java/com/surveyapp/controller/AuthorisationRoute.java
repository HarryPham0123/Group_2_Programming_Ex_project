package com.surveyapp.controller;

import com.surveyapp.model.LoginCredential;
import com.surveyapp.service.authorisation.AuthorisationService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthorisationRoute {
    private AuthorisationService authorisationService = new AuthorisationService();

    // Return a cookie containing a JWT that authorises a user if login credential is valid
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response authorise(LoginCredential loginCredential) {
        try {
            // Get a cookie containing a JWT that authorises given login credential
            NewCookie cookie = authorisationService.authoriseLoginCredential(loginCredential);

            // If login credentials is invalid, return STATUS CODE 500 - INTERNAL SERVER ERROR
            if(cookie == null){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
            // If login credentials is valid, return STATUS CODE 200 - OK
            else {
                return Response.status(Response.Status.OK).cookie(cookie).build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
