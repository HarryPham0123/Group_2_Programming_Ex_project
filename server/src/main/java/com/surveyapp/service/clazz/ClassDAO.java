package com.surveyapp.service.clazz;

import com.surveyapp.model.Class;
import com.surveyapp.service.DAO;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.*;
import java.util.function.Consumer;

public class ClassDAO implements DAO<Class> {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM class";
    private String getByCodeScript = "SELECT * FROM class WHERE Ccode = ?";
    private String saveScript = "INSERT INTO class (Ccode, size, Scode, Mcode) VALUES(?, ?, ?, ?)";
    private String updateScript = "UPDATE class SET Ccode = ?, size = ?, Scode = ?, Mcode = ? WHERE Ccode = ?";
    private String deleteScript = "DELETE FROM class WHERE  Ccode = ?";

    private void executeInTransaction(Consumer<Connection> action) {
        try {
            connection.setAutoCommit(false);
            action.accept(connection);
            connection.commit();
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            sqlException.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public List<Class> getAll() throws Exception {
        List<Class> classList = null;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllScript);
        classList = (List<Class>) ObjectConverter.toObject(Class.class, resultSet);

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return classList;
    }

    @Override
    public Optional<Class> get(String code) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
        preparedStatement.setString(1, code);
        ResultSet resultSet = preparedStatement.executeQuery();
        Class aClass = (Class) ObjectConverter.toObject(Class.class, resultSet);
        return Optional.ofNullable(aClass);
    }

    @Override
    public void save(Class aClass) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(saveScript);
        preparedStatement.setString(1, aClass.getCode());
        preparedStatement.setInt(2, aClass.getSize());
        preparedStatement.setString(3, aClass.getScode());
        preparedStatement.setString(4, aClass.getMcode());
        preparedStatement.executeUpdate();

        if(connection != null) {
            try {
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void update(String code, Class aClass) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(updateScript);
        preparedStatement.setString(1, aClass.getCode());
        preparedStatement.setInt(2, aClass.getSize());
        preparedStatement.setString(3, aClass.getScode());
        preparedStatement.setString(4, aClass.getMcode());
        preparedStatement.setString(5, code);
        preparedStatement.executeUpdate();

        if(connection != null) {
            try {
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String code) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(deleteScript);
        preparedStatement.setString(1, code);
        preparedStatement.executeUpdate();
        if(connection != null) {
            try {
                connection.close();
            } catch(Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
