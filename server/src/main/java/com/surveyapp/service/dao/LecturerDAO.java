package com.surveyapp.service.dao;

import com.surveyapp.model.Lecturer;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class LecturerDAO implements DAO<Lecturer> {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM lecturer";
    private String getByCodeScript = "SELECT * FROM lecturer WHERE Lcode = ?";
    private String saveScript = "INSERT INTO lecturer (Lcode, Lname) VALUES(?, ?)";
    private String updateScript = "UPDATE lecturer SET Lcode = ?, Lname = ? WHERE Lcode = ?";
    private String deleteScript = "DELETE FROM lecturer WHERE  Lcode = ?";

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
    public List<Lecturer> getAll() {
        List<Lecturer> lecturerList = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllScript);
            lecturerList = (List<Lecturer>) ObjectConverter.toObject(Lecturer.class, resultSet);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null)  {
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        return lecturerList;
    }

    @Override
    public Optional<Lecturer> get(String code) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            Lecturer lecturer = (Lecturer) ObjectConverter.toObject(Lecturer.class, resultSet);
            return Optional.ofNullable(lecturer);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Lecturer lecturer) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(saveScript);
            preparedStatement.setString(1, lecturer.getCode());
            preparedStatement.setString(2, lecturer.getName());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(String code, Lecturer lecturer) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateScript);
            preparedStatement.setString(1, lecturer.getCode());
            preparedStatement.setString(2, lecturer.getName());
            preparedStatement.setString(3, code);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally  {
            if(connection != null) {
                try {
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(String code) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteScript);
            preparedStatement.setString(1, code);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
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
}
