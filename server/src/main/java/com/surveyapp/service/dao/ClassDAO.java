package com.surveyapp.service.dao;

import com.surveyapp.model.AcademicYear;
import com.surveyapp.model.Class;
import com.surveyapp.model.Module;
import com.surveyapp.model.Semester;
import com.surveyapp.service.ModuleService;
import com.surveyapp.service.SemesterService;
import com.surveyapp.util.DBUtil;

import java.sql.*;
import java.util.*;
import java.util.function.Consumer;

public class ClassDAO implements  DAO<Class> {
    private Connection connection = new DBUtil().getConnection();
    private SemesterService semesterService = new SemesterService();
    private ModuleService moduleService = new ModuleService();
    private String getAllScript = "SELECT * FROM class";
    private String getByCodeScript = "SELECT * FROM class WHERE Ccode = ?";
    private String classCodeColumnName = "Ccode";
    private String classSizeColumnName = "size";
    private String classSemesterCodeColumnName = "Scode";
    private String classModuleCodeColumnName = "Mcode";

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

    public List<Class> getAll() {
        List<Class> classList = new ArrayList<Class>();

        /*
         * Retrieve all Academic Years and Modules from database
         * Convert them into instances of POJO class AcademicYear and Module
         */
        List<Semester> semesterList = semesterService.getAll();
        List<Module> moduleList = moduleService.getAll();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllScript);

            // Map each instance of Semester POJO to its code
            Map<String, Semester> semesterCodeMap= new HashMap<String, Semester>();
            for(int i = 0; i < semesterList.size(); i++) {
                Semester semester = semesterList.get(i);
                String semesterCode = semester.getCode();
                semesterCodeMap.put(semesterCode, semester);
            }

            // Map each instance of Module POJO to its code
            Map<String, Module> moduleCodeMap = new HashMap<String, Module>();
            for(int i = 0; i < moduleList.size(); i++) {
                Module module = moduleList.get(i);
                String moduleCode = module.getCode();
                moduleCodeMap.put(moduleCode, module);
            }

            while(resultSet.next()) {
                String classCode = resultSet.getString(classCodeColumnName);
                int classSize = resultSet.getInt(classSizeColumnName);
                String classSemesterCode = resultSet.getString(classSemesterCodeColumnName);
                String classModuleCode = resultSet.getString(classModuleCodeColumnName);

                Semester classSemester = semesterCodeMap.get(classSemesterCode);
                Module classModule = moduleCodeMap.get(classModuleCode);

                Class aClass = new Class (classCode, classSize, classSemester, classModule);
                classList.add(aClass);
            }
            return classList;
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
    public Optional<Class> get(String code) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getByCodeScript);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();

            String classCode = resultSet.getString(classCodeColumnName);
            int classSize = resultSet.getInt(classSizeColumnName);
            String classSemesterCode = resultSet.getString(classSemesterCodeColumnName);
            String classModuleCode = resultSet.getString(classModuleCodeColumnName);

            Semester classSemester = semesterService.get(classSemesterCode);
            Module classModule = moduleService.get(classModuleCode);

            Class aClass = new Class (classCode, classSize, classSemester, classModule);
            return Optional.ofNullable(aClass);
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
    public void save (Class aClass) {

    }

    @Override
    public void update (Class aClass) {

    }

    @Override
    public void delete (Class aClass) {

    }
}
