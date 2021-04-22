package com.surveyapp.service.semester;

import com.surveyapp.model.Semester;
import com.surveyapp.service.DAO;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.*;
import java.util.function.Consumer;

/**
 *
 * Create SemesterDAO to interact with table semester in DB
 * @author Tran Van Hung, Phan Cong Huy, Nguyen Dang Khoa
 *
 */

public class SemesterDAO implements DAO<Semester> {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM semester";
    private String getByCodeScript = "SELECT * FROM semester WHERE Scode = ?";
    private String saveScript = "INSERT INTO semester (Scode, AYcode) VALUES(?, ?)";
    private String updateScript = "UPDATE semester SET Scode = ?, AYcode = ? WHERE Scode = ?";
    private String deleteScript = "DELETE FROM semester WHERE  Scode = ?";

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
    public List<Semester> getAll()throws Exception  {
        List<Semester> semesterList = null;
        //Get database connection
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllScript);
        //Convert to Object type
        semesterList = (List<Semester>) ObjectConverter.toObject(Semester.class, resultSet);

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return semesterList;
    }

    @Override
    public Optional<Semester> get(String code) throws Exception {
        //Get database connection
        PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
        //Set parameters
        preparedStatement.setString(1, code);
        ResultSet resultSet = preparedStatement.executeQuery();
        //Convert to Object type
        Semester semester = (Semester) ObjectConverter.toObject(Semester.class, resultSet);
        return Optional.ofNullable(semester);
    }

    @Override
    public void save(Semester semester) throws Exception {
        //Get database connection
        PreparedStatement preparedStatement = connection.prepareStatement(saveScript);
        //Set parameters
        preparedStatement.setString(1, semester.getCode());
        preparedStatement.setString(2, semester.getAYCode());
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
    public void update(String code, Semester semester) throws Exception {
        //Get database connection
        PreparedStatement preparedStatement = connection.prepareStatement(updateScript);
        //Set parameters
        preparedStatement.setString(1, semester.getCode());
        preparedStatement.setString(2, semester.getAYCode());
        preparedStatement.setString(3, code);
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
        //Get database connection
        PreparedStatement preparedStatement = connection.prepareStatement(deleteScript);
        //Set parameters
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
