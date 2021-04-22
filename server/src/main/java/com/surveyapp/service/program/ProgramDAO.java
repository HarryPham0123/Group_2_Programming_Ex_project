package com.surveyapp.service.program;

import com.surveyapp.model.Program;
import com.surveyapp.service.DAO;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 *
 * Create ProgramDAO to interact with table program in DB
 * @author Tran Van Hung, Phan Cong Huy, Nguyen Dang Khoa
 *
 */

public class ProgramDAO implements DAO<Program> {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM program";
    private String getByCodeScript = "SELECT * FROM program WHERE Pcode = ?";
    private String saveScript = "INSERT INTO program (Pcode, Pname) VALUES(?, ?)";
    private String updateScript = "UPDATE program SET Pcode = ?, Pname = ? WHERE Pcode = ?";
    private String deleteScript = "DELETE FROM program WHERE  Pcode = ?";

    private void executeInTransaction(Consumer<Connection> action) throws Exception {
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
    public List<Program> getAll() throws Exception {
        List<Program> programList = null;
        //Get database connection
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllScript);
        //Convert to Object type
        programList = (List<Program>) ObjectConverter.toObject(Program.class, resultSet);

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return programList;
    }

    @Override
    public Optional<Program> get(String id) throws Exception{
        //Get database connection
        PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
        //Set parameters
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //Convert to Object type
        Program program = (Program) ObjectConverter.toObject(Program.class, resultSet);
        return Optional.ofNullable(program);
    }

    @Override
    public void save(Program program) throws Exception{
        //Get database connection
        PreparedStatement preparedStatement = connection.prepareStatement(saveScript);
        //Set parameters
        preparedStatement.setString(1, program.getCode());
        preparedStatement.setString(2, program.getName());
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
    public void update(String code, Program program)throws Exception {
        //Get database connection
        PreparedStatement preparedStatement = connection.prepareStatement(updateScript);
        //Set parameters
        preparedStatement.setString(1, program.getCode());
        preparedStatement.setString(2, program.getName());
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
    public void delete(String code)throws Exception {
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
