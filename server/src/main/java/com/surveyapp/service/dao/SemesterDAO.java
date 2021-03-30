package com.surveyapp.service.dao;

import com.surveyapp.model.AcademicYear;
import com.surveyapp.model.Semester;
import com.surveyapp.service.AcademicYearService;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.*;
import java.util.function.Consumer;

public class SemesterDAO implements DAO<Semester>{
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM semester";
    private String getByCodeScript = "SELECT * FROM semester WHERE Scode = ?";

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
    public List<Semester> getAll() {
        List<Semester> semesterList = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllScript);
            semesterList = (List<Semester>) ObjectConverter.toObject(Semester.class, resultSet);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return semesterList;
    }

    @Override
    public Optional<Semester> get(String code) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            Semester semester = (Semester) ObjectConverter.toObject(Semester.class, resultSet);
            return Optional.ofNullable(semester);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
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
    public void save (Semester semester) {

    }

    @Override
    public void update (Semester semester) {

    }

    @Override
    public void delete (Semester semester) {

    }
}
