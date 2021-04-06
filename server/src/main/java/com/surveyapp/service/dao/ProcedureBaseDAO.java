package com.surveyapp.service.dao;

import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ProcedureBaseDAO {
    protected Connection connection = new DBUtil().getConnection();
    protected String procedureQuery;
    protected CallableStatement statement = null;

    public String executeProcedure() throws Exception {
        try {
            System.out.println("[LOGGER] Query executed: " + procedureQuery);
            ResultSet resultSet = statement.executeQuery();
            String result = ObjectConverter.toJSON(resultSet);
            return result;
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
}
