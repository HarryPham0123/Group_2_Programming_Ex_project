package com.surveyapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * Mapping utilization to map the corresponding column into POJO (Plain old Java object) or JSON format
 * @version 0.0.2
 * @author Khoa Dang Nguyen
 * */
public class ObjMapper {
    /**
     * Map the table column into classes
     * @param mappingClass classes to be mapped into
     * @param resultSet result set retrieve from database
     * @return either object list or a object
     */
    @NotNull(message = "Can not be null")
    public static Object toObject(Class mappingClass, ResultSet resultSet) throws Exception {
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<Object> objectList = new ArrayList<Object>();

        while (resultSet.next()) {
            Object newInstance = mappingClass.getConstructor().newInstance();
            List<Field> fields = new ArrayList<Field>(Arrays.asList(newInstance.getClass().getDeclaredFields()));

            //Check whether fields are same length as a row
            if (fields.size() != metaData.getColumnCount()) {
                throw new IOException("Number of columns must be the same as number of fields!");
            }

            //Check whether fields exists annotation
            for (int i = 0; i < fields.size(); i++) {
                if (!fields.get(i).isAnnotationPresent(Column.class)) {
                    throw new IOException("No presentation of annotation!");
                }
            }

            //Loop through the row
            for (int i = 0; i < fields.size(); i++) {
                String entityColName = fields.get(i).getDeclaredAnnotation(Column.class).name();
                int tableColIndex = resultSet.findColumn(entityColName);
                //Hacking private fields
                fields.get(i).setAccessible(true);
                //Turns into date type if needed
                if (metaData.getColumnType(tableColIndex) == Types.DATE) {
                    fields.get(i).set(newInstance, resultSet.getDate(tableColIndex).toLocalDate());
                }
                else {
                    fields.get(i).set(newInstance, resultSet.getObject(tableColIndex));
                }
            }
            //Add to the list
            objectList.add(newInstance);
        }
        //Return a object if the size equals to 1
        return objectList.size() == 1 ? objectList.get(0) : objectList;
    }
    public static String toJSON(Object mappingObject) {
        try {
            return new ObjectMapper().writeValueAsString(mappingObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
