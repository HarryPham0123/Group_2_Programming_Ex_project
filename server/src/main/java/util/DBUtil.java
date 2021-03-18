package util;

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
 * @return */
public class DBUtil {
    private Properties config = new Properties();
    private String username, password, host;

    //Configs at initialization
    public DBUtil() {
        try {
            //Get properties
            InputStream inputStream = new FileInputStream("src/main/resources/database.properties");
            config.load(inputStream);

            //Configuration
            username = config.getProperty("username");
            password = config.getProperty("password");
            host = config.getProperty("host");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(username, password, host);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }
}
