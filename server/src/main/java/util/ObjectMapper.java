package util;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;

public class ObjectMapper {
    /**
     * Map the table column into classes
     * @param mappingClass classes to be mapped into
     * @param resultSet result set retrieve from database
     * @return either object list or a object
     *
     * @version 0.0.1
     * @author Nguyen Dang Khoa*/
    public static Object mapToObject(Class mappingClass, ResultSet resultSet) throws Exception {
        //Check input arguments
        if (mappingClass == null && resultSet == null) {
            throw new NullPointerException("There is no class and result set to map");
        }

        ResultSetMetaData metaData = resultSet.getMetaData();
        List<Object> objectList = new ArrayList<Object>();

        while (resultSet.next()) {
            Object newInstance = mappingClass.getConstructor().newInstance();
            List<Field> fields = new ArrayList<Field>(Arrays.asList(newInstance.getClass().getDeclaredFields()));

            //Check whether fields are same length as a row
            if (fields.size() != metaData.getColumnCount()) {
                throw new IOException("The table's column doesn't match the object member variables");
            }

            //Add variable value to the fields
            int entityToCheck = fields.size();
            while (entityToCheck != 0) {
                //Loop through the row
                for (int i = 0; i < fields.size(); i++) {
                    String entityColName = fields.get(i).getDeclaredAnnotation(Column.class).name();
                    //Check whether column name as same as field name
                    for (int j = 1; j < metaData.getColumnCount() + 1; j++) {
                        String tableColName = metaData.getColumnName(j);
                        //Check if the column name is matched
                        if (entityColName.equals(tableColName)) {
                            fields.get(i).setAccessible(true);
                            //Turns into date type if needed
                            if (metaData.getColumnType(j) == Types.DATE) {
                                fields.get(i).set(newInstance, resultSet.getDate(j).toLocalDate());
                            }
                            else {
                                fields.get(i).set(newInstance, resultSet.getObject(j));
                            }
                        }
                    }
                    entityToCheck--;
                }
            }
            //Add to the list
            objectList.add(newInstance);
        }
        //Return a object if the size equals to 1
        return objectList.size() == 1 ? objectList.get(0) : objectList;
    }

    public static String mapToJSON(Object mapObject) {
        return new Gson().toJson(mapObject);
    }
}
