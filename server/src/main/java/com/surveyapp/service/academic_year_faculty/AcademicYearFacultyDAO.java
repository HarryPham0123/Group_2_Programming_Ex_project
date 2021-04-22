package com.surveyapp.service.academic_year_faculty;

import com.surveyapp.model.AcademicYearFaculty;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AcademicYearFacultyDAO {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT AYcode, Fcode FROM ay_fac";
    private String getByCodeScript = "SELECT AYcode, Fcode FROM ay_fac WHERE AYcode = ?";
    private String saveScript = "{CALL AYcode_Fcode(?, ?, 'insert')}";
    private String deleteScript = "{CALL AYcode_Fcode(?, ?, 'delete')}";

    public List<AcademicYearFaculty> getAll() {
        List<AcademicYearFaculty> academicYearFacultyList = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllScript);
            academicYearFacultyList = (List<AcademicYearFaculty>) ObjectConverter.toObject(AcademicYearFaculty.class, resultSet);
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
        return academicYearFacultyList;
    }


    public Optional<AcademicYearFaculty> get(String academicYearcode){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
            preparedStatement.setString(1, academicYearcode);
            ResultSet resultSet = preparedStatement.executeQuery();
            AcademicYearFaculty academicYearFaculty = (AcademicYearFaculty) ObjectConverter.toObject(AcademicYearFaculty.class, resultSet);
            return Optional.ofNullable(academicYearFaculty);
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


    public String save(AcademicYearFaculty academicYearFaculty) throws Exception{
        String message = "Success";
        try {
            CallableStatement statement = connection.prepareCall(saveScript);
            statement.setString(1, academicYearFaculty.getAYcode());
            statement.setString(2, academicYearFaculty.getFcode());
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


    public String delete(AcademicYearFaculty academicYearFaculty) throws Exception{
        String message = "Success";
        try {
            CallableStatement statement = connection.prepareCall(deleteScript);
            statement.setString(1, academicYearFaculty.getAYcode());
            statement.setString(2, academicYearFaculty.getFcode());
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
