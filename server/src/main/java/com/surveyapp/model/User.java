package com.surveyapp.model;

import javax.ws.rs.FormParam;

public class User {
    @FormParam("username")
    private String username;
    @FormParam("password")
    private  String password;
    @FormParam("role")
    private String role;

    public String getUsername(){
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
