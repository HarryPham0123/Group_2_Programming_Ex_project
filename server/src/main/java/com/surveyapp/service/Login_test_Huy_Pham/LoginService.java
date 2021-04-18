package com.surveyapp.service.Login_test_Huy_Pham;

import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
public class LoginService {
    private Connection connection = new DBUtil().getConnection();
    private String query1 = "CALL pe2018.get_role(?,?)";


    public Optional<LoginModel> verify(String username,String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Printing results set"+resultSet);
            System.out.println("Reached 1");
            LoginModel data = (LoginModel) ObjectConverter.toObject(LoginModel.class, resultSet);
            System.out.println("Reached 2");
            System.out.println(data.getCCode()+" "+data.getValid()+" "+data.getLcode()+" ");
            return Optional.ofNullable(data);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
