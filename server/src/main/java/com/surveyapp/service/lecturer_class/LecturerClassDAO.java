package com.surveyapp.service.lecturer_class;

import com.surveyapp.model.Lecturer;
import com.surveyapp.model.LecturerClass;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class LecturerClassDAO {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "SELECT * FROM lecturer_in_class";
    private String getByCodeScript = "SELECT * FROM lecturer_in_class WHERE Lcode = ?";
    private String saveScript = "{CALL Ccode_Lcode(?, ?, 'insert')}";
    private String deleteScript = "{CALL Ccode_Lcode(?, ?, 'delete')}";

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

    public List<LecturerClass> getAll(){
        List<LecturerClass> lecturerClassList = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllScript);
            lecturerClassList = (List< LecturerClass>) ObjectConverter.toObject(LecturerClass.class, resultSet);
        } catch(Exception exception) {
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

        return lecturerClassList;
    }


    public Optional<LecturerClass> get(String lecturerCode){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
            preparedStatement.setString(1, lecturerCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            LecturerClass lecturerClass = (LecturerClass) ObjectConverter.toObject(Lecturer.class, resultSet);
            return Optional.ofNullable(lecturerClass);
        } catch(Exception exception) {
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


    public String save(LecturerClass lecturerClass) throws Exception{
        String message = "Success";
        try{
            CallableStatement statement = connection.prepareCall(saveScript);
            statement.setString(1, lecturerClass.getCcode());
            statement.setString(2, lecturerClass.getLcode());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.getMetaData().getColumnCount() > 0) {
                resultSet.next();
                message = resultSet.getString("message");
            }
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return message;
    }



    public String delete(LecturerClass lecturerClass) throws Exception{
        String message = "Success";
        try {
            CallableStatement statement = connection.prepareCall(deleteScript);
            statement.setString(1, lecturerClass.getCcode());
            statement.setString(2, lecturerClass.getLcode());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.getMetaData().getColumnCount() > 0) {
                resultSet.next();
                String messsage = resultSet.getString("message");
            }
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch(Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return message;
    }
}
