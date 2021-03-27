package com.surveyapp.util;

import javax.json.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

public class JSONMapper {
    /**
     * Map the table columns into a JSON array
     * @param resultSet result set retrieve from database
     * @return a JSON array
     */
    @NotNull(message = "Can not be null")
    public static Response toJSON(ResultSet resultSet) throws Exception{
        ResultSetMetaData metaData = resultSet.getMetaData();


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
                else if (columnType == 12) {
                    fieldStringValue = resultSet.getString(i);
                    entryModel.add(fieldName, fieldStringValue);
                }

            }
            JsonObject entry = entryModel.build();
            entryListModel.add(entry);
        }
        JsonArray entryList = entryListModel.build();
        return Response.ok().entity(entryList.toString()).build();
    }
}
