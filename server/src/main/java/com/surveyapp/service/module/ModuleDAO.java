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
    private String saveScript = "INSERT INTO module (Mcode, Mname) VALUES (?,?)";
    private String updateScript = "UPDATE module SET Mcode = ? , Mname = ? WHERE Mcode = ?";
    private String deleteScript = "DELETE FROM module WHERE Mcode = ? ";
    @Override
    public List<Module> getAll() throws Exception {
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
    public Optional<Module> get(String code) throws Exception{
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
    public void save(Module module) throws Exception{

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveScript);
            preparedStatement.setString(1, module.getCode());
            preparedStatement.setString(2, module.getName());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();

        }

    }

    @Override
    public void update(String code, Module module) throws Exception{

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateScript);
            preparedStatement.setString(1, module.getCode());
            preparedStatement.setString(2, module.getName());
            preparedStatement.setString(3, code);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();

        }

    }

    @Override
    public void delete(String code) throws Exception{
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteScript);
            preparedStatement.setString(1, code);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
