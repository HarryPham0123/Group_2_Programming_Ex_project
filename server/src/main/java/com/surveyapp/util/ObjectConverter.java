package com.surveyapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.json.*;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;

/**
 * Mapping utilization to map the corresponding column into POJO (Plain old Java object) or JSON format
 * @version 0.0.2
 * @author Khoa Dang Nguyen
 * */
public class ObjectConverter {
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
    /**
     * Map the table columns into a JSON array
     * @param resultSet result set retrieve from database
     * @return a JSON array
     */
    @NotNull(message = "Can not be null")
    public static String toJSON(ResultSet resultSet) throws Exception{
        ResultSetMetaData metaData = resultSet.getMetaData();

        if (metaData.getColumnCount() == 0) {
            return null;
        }

        JsonArrayBuilder entryListModel = Json.createArrayBuilder();
        while(resultSet.next()) {
            JsonObjectBuilder entryModel = Json.createObjectBuilder();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String fieldName = metaData.getColumnName(i);
                int columnType = metaData.getColumnType(i);
                String fieldStringValue = "";
                int fieldNumericValue = 0;

                // Column contains integer values
                if (columnType == 4) {
                    fieldNumericValue = resultSet.getInt(i);
                    entryModel.add(fieldName, fieldNumericValue);
                }
                // Column contains string values
                else if (columnType == 12 || columnType == -1) {
                    fieldStringValue = resultSet.getString(i);
                    entryModel.add(fieldName, fieldStringValue);
                }

            }
            JsonObject entryList = entryModel.build();
            entryListModel.add(entryList);
        }
        return entryListModel.build().toString();
    }

    @NotNull(message = "Can not be null")
    public static String toSummaryJSON(ResultSet resultSet) throws Exception {
        ResultSetMetaData metaData = resultSet.getMetaData();

        if(metaData.getColumnCount() == 0) {
            return null;
        }

        JsonArrayBuilder entryListModel = Json.createArrayBuilder();
        while(resultSet.next()) {
            String keyJSON = resultSet.getString(1);
            String valueJSON = resultSet.getString(2);
            JsonObject entry = Json.createObjectBuilder().add(keyJSON, valueJSON).build();
            entryListModel.add(entry);
        }

        return entryListModel.build().toString();
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
