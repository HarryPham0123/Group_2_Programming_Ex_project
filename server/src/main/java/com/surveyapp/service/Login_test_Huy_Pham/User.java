package com.surveyapp.service.Login_test_Huy_Pham;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class User {
    private String username;
    private String password;
    private Optional<LoginModel> roles;
    private List<String> roles_string;

    public User(){};
    public User (String u,String p, Optional<LoginModel> d){
        this.username=u;
        this.password=p;
        this.roles=d;
        roles_string=d.get().LoginToString();
    };

    public String getUsername() {
        return username;
    }
    public void setUsername(String u){
        this.username=u;
    }

    public  List<String> getRole() {
        roles_string=this.roles.get().LoginToString();
        return roles_string;
    }
    public void setRoles(List<String> d){
        this.roles_string =d;
    }
}