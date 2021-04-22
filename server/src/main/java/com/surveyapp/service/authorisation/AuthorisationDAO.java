package com.surveyapp.service.authorisation;

import com.surveyapp.model.AuthorisedUser;
import com.surveyapp.model.LoginCredential;
import com.surveyapp.util.DBUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.*;

import javax.crypto.SecretKey;
import javax.naming.NamingException;
import javax.ws.rs.core.NewCookie;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthorisationDAO {
    private String secretString = "Ih9Zh7BBGpyIkIZmK/5qdBQmfyw+EEsBUy+M6/n3Woux9y1Do7ql1mhayBL01+FKG5FpoJCnXSzivk5WY/egTg==";
    private SecretKey key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));

    /**
     * Decide if given login credentials is valid
     * If valid then return detailed information of authorised user. Otherwise, return null
     */
    private AuthorisedUser verifyLogin(LoginCredential loginCredential) throws Exception{
        // Create new connection to DB
        Connection connection = new DBUtil().getConnection();

        try {
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
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    // Build JWT by using detailed information of authorised user as detailed information
    private String issueToken(AuthorisedUser authUser) throws NamingException {
        /*
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretString = Encoders.BASE64.encode(key.getEncoded());

         */
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

    // Created JWT is put into a cookie
    private NewCookie issueCookie(String authToken) throws Exception{
        NewCookie cookie = new NewCookie("sqmvsa", authToken, "/", "", "auth_token", NewCookie.DEFAULT_MAX_AGE, false);

        return cookie;
    }

    /**
     * Decide if given login credentials is valid
     * If valid then return a cookie containing a JWT . Otherwise, return null
     */
    public NewCookie authoriseLoginCredential(LoginCredential loginCredential) throws Exception{
        AuthorisedUser authUser = verifyLogin(loginCredential);
        if(authUser == null) {
            return null;
        } else {
            String authToken =  issueToken(authUser);
            NewCookie cookie = issueCookie(authToken);
            return cookie;
        }
    }
}
