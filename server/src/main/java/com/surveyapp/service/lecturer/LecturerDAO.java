package com.surveyapp.service.lecturer;

import com.surveyapp.model.Lecturer;
import com.surveyapp.service.DAO;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class LecturerDAO implements DAO<Lecturer> {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM lecturer";
    private String getByCodeScript = "SELECT * FROM lecturer WHERE Lcode = ?";
    private String saveScript = "INSERT INTO lecturer (Lcode, Lname, user_id) VALUES(?, ?, ?)";
    private String updateScript = "UPDATE lecturer SET Lcode = ?, Lname = ? WHERE Lcode = ?";
    private String deleteScript = "DELETE FROM lecturer WHERE  Lcode = ?";

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
    public List<Lecturer> getAll() throws Exception {
        List<Lecturer> lecturerList = null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAllScript);
        lecturerList = (List<Lecturer>) ObjectConverter.toObject(Lecturer.class, resultSet);

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return lecturerList;
    }

    @Override
    public Optional<Lecturer> get(String code) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
        preparedStatement.setString(1, code);
        ResultSet resultSet = preparedStatement.executeQuery();
        Lecturer lecturer = (Lecturer) ObjectConverter.toObject(Lecturer.class, resultSet);
        return Optional.ofNullable(lecturer);
    }

    @Override
    public void save(Lecturer lecturer) throws Exception {
        try {
            // Select last row from table user in database
            String getLastUserRowQuery = "SELECT * FROM user ORDER BY user_id DESC LIMIT 1";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getLastUserRowQuery);

            // Enter first row of resultSet
            resultSet.next();

            /*
             * Get number in user_id of last row in table user
             * For example: user_id is U050r
             * Then the number in user_id is 50
             */
            String lastRowUserID = resultSet.getString("user_id");
            String numberLastRowUserIDStr = lastRowUserID.substring(1, 4);
            int numberLastRowUserID = Integer.parseInt(numberLastRowUserIDStr);

            // Create user_id for new lecturer
            int newNumberUserID = numberLastRowUserID + 1;
            String newNumberUserIDStr = Integer.toString(newNumberUserID);
            if (newNumberUserID < 10) {
                newNumberUserIDStr = "00" + newNumberUserIDStr;
            } else if (newNumberUserID < 100) {
                newNumberUserIDStr = "0" + newNumberUserIDStr;
            }
            String newUserID = "U" + newNumberUserIDStr + "r";
            lecturer.setUserID(newUserID);

            // Name of lecturer without whitespace
            String lecturerName = lecturer.getName().replace(" ", "");

            //Create Email for new lecturer
            String email = lecturerName + "@gmail.com";

            // Create User For Lecturer
            String createUserScript = "INSERT INTO user(user_id, full_name, gender, email) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(createUserScript);
            preparedStatement.setString(1, newUserID);
            preparedStatement.setString(2, lecturer.getName());
            preparedStatement.setString(3, "male");
            preparedStatement.setString(4, email);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            // Insert new lecturer
            preparedStatement = connection.prepareStatement(saveScript);
            preparedStatement.setString(1, lecturer.getCode());
            preparedStatement.setString(2, lecturer.getName());
            preparedStatement.setString(3, lecturer.getUserID());
            preparedStatement.executeUpdate();

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
    public void update(String code, Lecturer lecturer) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(updateScript);
        preparedStatement.setString(1, lecturer.getCode());
        preparedStatement.setString(2, lecturer.getName());
        preparedStatement.setString(3, code);
        preparedStatement.executeUpdate();

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String code) throws Exception {
        String getUserIDQuery = "SELECT user_id FROM lecturer WHERE Lcode = ?";
        String deleteUserQuery = "DELETE FROM user WHERE user_id = ?";
        try {
            //  Get user_id of lecturer will be deleted
            PreparedStatement preparedStatement = connection.prepareStatement(getUserIDQuery);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String correspondingUserID = resultSet.getString("user_id");
            preparedStatement.close();

            // Delete a lecturer with given code
            preparedStatement = connection.prepareStatement(deleteScript);
            preparedStatement.setString(1, code);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            // Delete a user with given user_id
            preparedStatement = connection.prepareStatement(deleteUserQuery);
            preparedStatement.setString(1, correspondingUserID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
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

}
