package com.surveyapp.service.dao;

import com.surveyapp.model.Program;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ProgramDAO implements DAO<Program> {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM program";

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
    public List<Program> getAll() {

        List<Program> programList = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllScript);
            programList = (List<Program>) ObjectConverter.toObject(Program.class, resultSet);
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
        return programList;
    }

    @Override
    public Optional<Program> get(String id) {
        return Optional.empty();
    }

    @Override
    public void save(Program program) {

    }

    @Override
    public void update(String code, Program program) {

    }

    @Override
    public void delete(String code) {

    }

}
