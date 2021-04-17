package com.surveyapp.service.faculty;
import com.surveyapp.model.Faculty;
import com.surveyapp.service.DAO;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class FacultyDAO implements DAO<Faculty> {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM faculty";
    private String getByCodeScript = "SELECT * FROM faculty WHERE Fcode = ?";
    private String saveScript = "INSERT INTO faculty (Fcode, Fname) VALUES(?, ?)";
    private String updateScript = "UPDATE faculty SET Fcode = ?, Fname = ? WHERE Fcode = ?";
    private String deleteScript = "DELETE FROM faculty WHERE  Fcode = ?";

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
    public Optional<Faculty> get(String code) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            Faculty faculty = (Faculty) ObjectConverter.toObject(Faculty.class, resultSet);
            return Optional.ofNullable(faculty);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Faculty faculty) {
        boolean isSaved = true;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(saveScript);
            preparedStatement.setString(1, faculty.getCode());
            preparedStatement.setString(2, faculty.getName());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
            isSaved = false;
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return isSaved;
    }

    @Override
    public boolean update(String code, Faculty faculty) {
        boolean isUpdated = true;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateScript);
            preparedStatement.setString(1, faculty.getCode());
            preparedStatement.setString(2, faculty.getName());
            preparedStatement.setString(3, code);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
            isUpdated = false;
        } finally  {
            if(connection != null) {
                try {
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return isUpdated;
    }

    @Override
    public boolean delete(String code) {
        boolean isDeleted = true;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteScript);
            preparedStatement.setString(1, code);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
            isDeleted = false;
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return isDeleted;
    }
}
