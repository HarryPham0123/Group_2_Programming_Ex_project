package com.surveyapp.service.dao;

import com.surveyapp.model.Lecturer;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;

public class ProcedureDAO {
    private Connection connection = new DBUtil().getConnection();
    private String procedureQuery = "{CALL %s(%s, %s, %s, %s, %s, %s, %s)}";

    public ProcedureDAO(String procedureName) {
        this.procedureQuery = String.format(procedureQuery, procedureName, "null", "null", "null", "null", "null", "null", "null");
    }

    public String getAll() {
        try {
            CallableStatement statement = connection.prepareCall(procedureQuery);
            ResultSet resultSet = statement.executeQuery(procedureQuery);
            String result = ObjectConverter.toJSON(resultSet);
            return result;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
