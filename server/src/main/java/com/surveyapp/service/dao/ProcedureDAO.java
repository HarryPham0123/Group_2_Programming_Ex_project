package com.surveyapp.service.dao;

import com.surveyapp.model.Lecturer;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;

public class ProcedureDAO {
    private Connection connection = new DBUtil().getConnection();
    private String generalInformationQuery = "{CALL general_information(%s, %s, %s, %s, %s, %s, %s)}";

    private String executeProcedure(String procedureCallQuery) {
        try {
            CallableStatement statement = connection.prepareCall(procedureCallQuery);
            ResultSet resultSet = statement.executeQuery();
            String result = ObjectConverter.toJSON(resultSet);
            return result;
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

    public String getAll() {
        generalInformationQuery = String.format(generalInformationQuery, "null", "null", "null", "null", "null", "null", "null");
        String jsonString = executeProcedure(generalInformationQuery);
        return jsonString;
    }

    public String getAcademicYear(String code) {
        String codeFormat = "'" + code + "'";
        generalInformationQuery = String.format(generalInformationQuery, codeFormat, "null", "null", "null", "null", "null", "null");
        String jsonString = executeProcedure(generalInformationQuery);
        return jsonString;
    }

    public String getSemester(String code) {
        String codeFormat = "'" + code + "'";
        generalInformationQuery = String.format(generalInformationQuery, "null",  codeFormat, "null", "null", "null", "null", "null");
        String jsonString = executeProcedure(generalInformationQuery);
        return jsonString;
    }

    public String getFaculty(String code) {
        String codeFormat = "'" + code + "'";
        generalInformationQuery = String.format(generalInformationQuery, "null",  "null", codeFormat, "null", "null", "null", "null");
        String jsonString = executeProcedure(generalInformationQuery);
        return jsonString;
    }

    public String getProgram(String code) {
        String codeFormat = "'" + code + "'";
        generalInformationQuery = String.format(generalInformationQuery, "null",  "null", "null", codeFormat, "null", "null", "null");
        String jsonString = executeProcedure(generalInformationQuery);
        return jsonString;
    }

    public String getModule(String code) {
        String codeFormat = "'" + code + "'";
        generalInformationQuery = String.format(generalInformationQuery, "null",  "null", "null", "null", codeFormat, "null", "null");
        String jsonString = executeProcedure(generalInformationQuery);
        return jsonString;
    }

    public String getLecturer(String code) {
        String codeFormat = "'" + code + "'";
        generalInformationQuery = String.format(generalInformationQuery, "null",  "null", "null", "null", "null", codeFormat, "null");
        String jsonString = executeProcedure(generalInformationQuery);
        return jsonString;
    }

    public String getClass(String code) {
        String codeFormat = "'" + code + "'";
        generalInformationQuery = String.format(generalInformationQuery, "null",  "null", "null", "null", "null", "null", codeFormat);
        String jsonString = executeProcedure(generalInformationQuery);
        return jsonString;
    }
}
