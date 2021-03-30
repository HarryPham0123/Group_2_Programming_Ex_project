package com.surveyapp.service.dao;

import com.surveyapp.model.Module;
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

    }

    @Override
    public void update(Module module) {

    }

    @Override
    public void delete(Module module) {

    }
}
