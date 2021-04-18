package com.surveyapp.service.Login_Testing_Cong_Huy;

import com.surveyapp.service.Login_Testing_Cong_Huy.LoginCredential;
import com.surveyapp.service.Login_Testing_Cong_Huy.AuthorisationService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthorisationRoute {
    private AuthorisationService authorisationService = new AuthorisationService();
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response authorise(LoginCredential loginCredential) {
        try {
            NewCookie cookie = authorisationService.authoriseLoginCredential(loginCredential);
            if(cookie.equals(null)){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            } else {
                return Response.status(Response.Status.OK).cookie(cookie).build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
