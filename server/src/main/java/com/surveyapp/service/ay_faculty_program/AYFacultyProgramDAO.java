package com.surveyapp.service.ay_faculty_program;

import com.surveyapp.model.AYFacultyProgram;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AYFacultyProgramDAO {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = " SELECT AYcode, Fcode, Pcode FROM ay_fac JOIN ay_fac_p ON ay_fac.AYFcode = ay_fac_p.AYFcode";
    private String getByCodeScript = " SELECT AYcode, Fcode, Pcode FROM ay_fac JOIN ay_fac_p ON ay_fac.AYFcode = ay_fac_p.AYFcode WHERE AYcode = ?";
    private String saveScript = "{CALL AYcode_Fcode_Pcode(?, ?, ?, 'insert')}";
    private String deleteScript = "{CALL AYcode_Fcode_Pcode(?, ?, ?, 'delete')}";

    public List<AYFacultyProgram> getAll() {
        List<AYFacultyProgram> ayFacultyProgramList = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllScript);
            ayFacultyProgramList = (List<AYFacultyProgram>) ObjectConverter.toObject(AYFacultyProgram.class, resultSet);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        return ayFacultyProgramList;
    }


    public Optional<AYFacultyProgram> get(String academicYearcode){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
            preparedStatement.setString(1, academicYearcode);
            ResultSet resultSet = preparedStatement.executeQuery();
            AYFacultyProgram ayFacultyProgram = (AYFacultyProgram) ObjectConverter.toObject(AYFacultyProgram.class, resultSet);
            return Optional.ofNullable(ayFacultyProgram);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
    }


    public String save(AYFacultyProgram ayFacultyProgram) throws Exception{
        String message = "Success";
        try {
            CallableStatement statement = connection.prepareCall(saveScript);
            statement.setString(1, ayFacultyProgram.getAYcode());
            statement.setString(2, ayFacultyProgram.getFcode());
            statement.setString(3, ayFacultyProgram.getPcode());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.getMetaData().getColumnCount() > 0) {
                resultSet.next();
                message = resultSet.getString("message");
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        return message;
    }


    public String delete(AYFacultyProgram ayFacultyProgram) throws Exception{
        String message = "Success";
        try {
            CallableStatement statement = connection.prepareCall(deleteScript);
            statement.setString(1, ayFacultyProgram.getAYcode());
            statement.setString(2, ayFacultyProgram.getFcode());
            statement.setString(3, ayFacultyProgram.getPcode());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.getMetaData().getColumnCount() > 0) {
                resultSet.next();
                message = resultSet.getString("message");
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        return message;
    }
}
