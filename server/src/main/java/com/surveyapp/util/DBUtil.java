package com.surveyapp.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Use to connect to database
 * @author Nguyen Dang Khoa
 * @return create connection to database*/
public class DBUtil {
    //Configs at initialization
    public Connection getConnection() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/pe");
            return ds.getConnection();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        } catch (NamingException namingException) {
            namingException.printStackTrace();
            return null;
        }
    }
}
