package com.surveyapp.service.Login_test_Cong_Huy;

import com.surveyapp.util.DBUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.naming.NamingException;
import javax.ws.rs.core.NewCookie;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthorisationDAO {
    private AuthorisedUser verifyLogin(LoginCredential loginCredential) throws Exception{

        // Create new connection to DB
        Connection connection = new DBUtil().getConnection();

        String verifyingQuery = "{CALL get_role(?, ?)}";

        PreparedStatement statement = connection.prepareStatement(verifyingQuery);
        statement.setString(1, loginCredential.getUsername());
        statement.setString(2, loginCredential.getPassword());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        int valid = resultSet.getInt("valid");
        if(valid == 0) {
            return null;
        } else {
            String lecturerCode = resultSet.getString("lecturer");
            String classCode = resultSet.getString("class_code");
            int coordinatorCode = resultSet.getInt("coordinator");
            String program = resultSet.getString("program");
            int dean = resultSet.getInt("dean");
            String faculty = resultSet.getString("faculty");

            AuthorisedUser authUser = new AuthorisedUser(lecturerCode, classCode, coordinatorCode, program, dean, faculty);

            return authUser;
        }
    }

    private String issueToken(AuthorisedUser authUser) throws NamingException {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        String secretString = Encoders.BASE64.encode(key.getEncoded());

        System.out.println(secretString);

        String authToken = Jwts.builder()
                .claim("lecturerCode", authUser.getLecturerCode())
                .claim("classCode", authUser.getClassCode())
                .claim("coordinatorCode", authUser.getCoordinatorCode())
                .claim("program", authUser.getProgram())
                .claim("deanCode", authUser.getDeanCode())
                .claim("faculty", authUser.getFaculty())
                .signWith(key)
                .compact();

        return authToken;
    }

    private NewCookie issueCookie(String authToken) throws Exception{
        NewCookie cookie = new NewCookie("sqmvsa", authToken, "/", "", "auth_token", NewCookie.DEFAULT_MAX_AGE, false);

        return cookie;
    }

    public NewCookie authoriseLoginCredential(LoginCredential loginCredential) throws Exception{
        AuthorisedUser authUser = verifyLogin(loginCredential);
        if(authUser.equals(null)) {
            return null;
        } else {
            String authToken =  issueToken(authUser);
            NewCookie cookie = issueCookie(authToken);
            return cookie;
        }
    }
}
