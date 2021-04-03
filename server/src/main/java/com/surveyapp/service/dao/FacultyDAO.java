package com.surveyapp.service.dao;
import com.surveyapp.model.Faculty;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
public class FacultyDAO implements DAO<Faculty> {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM faculty";

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


    @Override
    public List<Faculty> getAll() {
        List<Faculty> facultyList = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllScript);
            facultyList = (List<Faculty>) ObjectConverter.toObject(Faculty.class, resultSet);
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
        return facultyList;
    }

    @Override
    public Optional<Faculty> get(String id) {
        return Optional.empty();
    }

    @Override
    public void save(Faculty faculty) {

    }

    @Override
    public void update(String code, Faculty faculty) {

    }

    @Override
    public void delete(String code) {

    }
}
