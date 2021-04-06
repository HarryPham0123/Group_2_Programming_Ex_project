package com.surveyapp.service.module;

import com.surveyapp.model.Module;
import com.surveyapp.service.DAO;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ModuleDAO implements DAO<Module> {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM module";
    private String getByCodeScript = "SELECT * FROM module WHERE Mcode = ?";
    @Override
    public List<Module> getAll() {
        List<Module> modulelist = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllScript);
            modulelist = (List<Module>) ObjectConverter.toObject(Module.class, resultSet);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        return modulelist;
    }

    @Override
    public Optional<Module> get(String code) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            Module module = (Module) ObjectConverter.toObject(Module.class, resultSet);
            return Optional.ofNullable(module);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Module module) {
        String script = "INSERT INTO  module VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(script);
            preparedStatement.setString(1, module.getCode());
            preparedStatement.setString(2, module.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(String code, Module module) {
        String script = "UPDATE module SET Mcode = ? , Mname = ? WHERE Mcode = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(script);
            preparedStatement.setString(1, module.getCode());
            preparedStatement.setString(2, module.getName());
            preparedStatement.setString(3, code);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(String code) {
        String script = "DELETE FROM module WHERE Mcode = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(script);
            preparedStatement.setString(1, code);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
