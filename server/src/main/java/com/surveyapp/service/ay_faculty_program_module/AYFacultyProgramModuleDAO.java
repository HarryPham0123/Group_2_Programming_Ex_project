package com.surveyapp.service.ay_faculty_program_module;

import com.surveyapp.model.AYFacultyProgramModule;
import com.surveyapp.util.DBUtil;
import com.surveyapp.util.ObjectConverter;

import java.sql.*;
import java.util.List;
import java.util.Optional;


public class AYFacultyProgramModuleDAO {
    private Connection connection = new DBUtil().getConnection();
    private String getAllScript = "  SELECT AYcode, Fcode, Pcode, Mcode FROM ay_fac JOIN ay_fac_p JOIN ay_fac_pm ON ay_fac.AYFcode = ay_fac_p.AYFcode AND ay_fac_p.AYFPcode = ay_fac_pm.AYFPcode";
    private String getByCodeScript = "  SELECT AYcode, Fcode, Pcode, Mcode FROM ay_fac JOIN ay_fac_p JOIN ay_fac_pm ON ay_fac.AYFcode = ay_fac_p.AYFcode AND ay_fac_p.AYFPcode = ay_fac_pm.AYFPcode WHERE AYcode = ?";
    private String saveScript = "{CALL AYcode_Fcode_Pcode_Mcode(?, ?, ?, ?, 'insert')}";
    private String deleteScript = "{CALL AYcode_Fcode_Pcode_Mcode(?, ?, ?, ?, 'delete')}";

    public List<AYFacultyProgramModule> getAll() {
        List<AYFacultyProgramModule> ayFacultyProgramModuleList = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllScript);
            ayFacultyProgramModuleList = (List<AYFacultyProgramModule>) ObjectConverter.toObject(AYFacultyProgramModule.class, resultSet);
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
        return ayFacultyProgramModuleList;
    }


    public Optional<AYFacultyProgramModule> get(String academicYearcode){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
            preparedStatement.setString(1, academicYearcode);
            ResultSet resultSet = preparedStatement.executeQuery();
            AYFacultyProgramModule ayFacultyProgramModule = (AYFacultyProgramModule) ObjectConverter.toObject(AYFacultyProgramModule.class, resultSet);
            return Optional.ofNullable(ayFacultyProgramModule);
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


    public String save(AYFacultyProgramModule ayFacultyProgramModule) throws Exception{
        String message = "Success";
        try {
            CallableStatement statement = connection.prepareCall(saveScript);
            statement.setString(1, ayFacultyProgramModule.getAYcode());
            statement.setString(2, ayFacultyProgramModule.getFcode());
            statement.setString(3, ayFacultyProgramModule.getPcode());
            statement.setString(4, ayFacultyProgramModule.getMcode());
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


    public String delete(AYFacultyProgramModule ayFacultyProgramModule) throws Exception{
        String message = "Success";
        try {
            CallableStatement statement = connection.prepareCall(deleteScript);
            statement.setString(1, ayFacultyProgramModule.getAYcode());
            statement.setString(2, ayFacultyProgramModule.getFcode());
            statement.setString(3, ayFacultyProgramModule.getPcode());
            statement.setString(4, ayFacultyProgramModule.getMcode());
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
