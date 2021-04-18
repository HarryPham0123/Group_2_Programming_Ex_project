package com.surveyapp.service.academic_year;

import com.surveyapp.model.AcademicYear;
import com.surveyapp.service.DAO;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AcademicYearDAO implements DAO<AcademicYear> {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM academic_year";
    private String getByCodeScript = "SELECT * FROM academic_year WHERE AYcode = ?";
    private String saveScript = "INSERT INTO  academic_year(AYcode) VALUES (?)";
    private String deleteScript = "DELETE FROM academic_year WHERE AYcode = ? ";
    @Override
    public List<AcademicYear> getAll() throws Exception {
        List<AcademicYear> academicYearList = null;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllScript);
        academicYearList = (List<AcademicYear>) ObjectConverter.toObject(AcademicYear.class, resultSet);

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return academicYearList;
    }

    @Override
    public Optional<AcademicYear> get(String code) throws Exception { //code inputted by FE
        PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
        preparedStatement.setString(1, code);
        ResultSet resultSet = preparedStatement.executeQuery();
        AcademicYear academicYear = (AcademicYear) ObjectConverter.toObject(AcademicYear.class, resultSet);
        return Optional.ofNullable(academicYear);
    }

    @Override
    public void save(AcademicYear academicYear) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(saveScript);
        preparedStatement.setString(1, academicYear.getCode());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(String code, AcademicYear academicYear) throws Exception {

    }

    @Override
    public void delete(String code) throws Exception{
        PreparedStatement preparedStatement = connection.prepareStatement(deleteScript);
        preparedStatement.setString(1, code);
        preparedStatement.executeUpdate();
    }
}
