package com.surveyapp.service.Login_Testing_Cong_Huy;

import javax.ws.rs.core.NewCookie;

public class AuthorisationService {
    private AuthorisationDAO authorisationDAO = new AuthorisationDAO();

    public NewCookie authoriseLoginCredential(LoginCredential loginCredential) throws Exception{
        return authorisationDAO.authoriseLoginCredential(loginCredential);
    }
}
