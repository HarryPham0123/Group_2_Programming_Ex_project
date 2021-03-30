package com.surveyapp.service.dao;

import com.surveyapp.model.AcademicYear;
import com.surveyapp.model.Semester;
import com.surveyapp.service.AcademicYearService;
import com.surveyapp.util.DBUtil;

import java.sql.*;
import java.util.*;
import java.util.function.Consumer;

public class SemesterDAO implements DAO<Semester>{
    private Connection connection = new DBUtil().getConnection();
    private AcademicYearService academicYearService = new AcademicYearService();
    private String getAllScript = "SELECT * FROM semester";
    private String getByCodeScript = "SELECT * FROM semester WHERE Scode = ?";
    private String semesterCodeColumnName = "Scode";
    private String semesterAcademicYearCodeColumnName = "AYcode";

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
        List<Semester> semesterList = new ArrayList<Semester>();

        /*
         * Retrieve all Academic Years from database
         * Convert them into instances of POJO class AcademicYear
         */
        List<AcademicYear> academicYearList = academicYearService.getAll();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllScript);

            // Map each instance of AcademicYear class to its code
            Map<String, AcademicYear> academicYearCodeMap= new HashMap<String, AcademicYear>();
            for(int i = 0; i < academicYearList.size(); i++) {
                AcademicYear academicYear = academicYearList.get(i);
                String academicYearCode = academicYear.getAYcode();
                academicYearCodeMap.put(academicYearCode, academicYear);
            }

            while(resultSet.next()) {
                String semesterCode = resultSet.getString(semesterCodeColumnName);
                String semesterAcademicYearCode =resultSet.getString(semesterAcademicYearCodeColumnName);

                AcademicYear semesterAcademicYear = academicYearCodeMap.get(semesterAcademicYearCode);

                Semester semester = new Semester(semesterCode, semesterAcademicYear);
                semesterList.add(semester);
            }
            return semesterList;
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

    }

    @Override
    public Optional<Semester> get(String code) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            String semesterCode = resultSet.getString(semesterCodeColumnName);
            String semesterAcademicYearCode = resultSet.getString(semesterAcademicYearCodeColumnName);

            AcademicYear correspondingAcademicYear = academicYearService.get(semesterAcademicYearCode);

            Semester semester = new Semester(semesterCode, correspondingAcademicYear);
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
