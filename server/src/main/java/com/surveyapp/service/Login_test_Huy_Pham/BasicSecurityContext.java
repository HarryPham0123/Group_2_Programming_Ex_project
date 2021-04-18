package com.surveyapp.service.Login_test_Huy_Pham;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;


public class BasicSecurityContext implements SecurityContext {

    private User user;
    private boolean secure;

    public BasicSecurityContext(User user, boolean secure) {
        this.user = user;
        this.secure = secure;
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> user.getUsername();
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }


    @Override
    public boolean isSecure() {
        return secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}