package com.surveyapp.service.authorisation;

import com.surveyapp.model.LoginCredential;

import javax.ws.rs.core.NewCookie;

public class AuthorisationService {
    private AuthorisationDAO authorisationDAO = new AuthorisationDAO();

    // Get a cookie containing a JWT that authorises given login credential
    public NewCookie authoriseLoginCredential(LoginCredential loginCredential) throws Exception{
        return authorisationDAO.authoriseLoginCredential(loginCredential);
    }
}
