package com.poc.app.utils;

import com.alibaba.fastjson.JSONPath;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.poc.app.constant.TestConst;

public class JsonHelpers {
    private static final Logger logger = LoggerFactory.getLogger(JsonHelpers.class);

    /**
     * Get the schema from file
     *
     * @param schemaPath
     * @return Schema
     */
    public static Schema getJsonSchema(String schemaPath) {
        JSONObject jsonSchema = null;
        try (InputStream inputStream = JsonHelpers.class
                .getResourceAsStream(schemaPath)) {
            jsonSchema = new JSONObject(new JSONTokener(inputStream));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return SchemaLoader.load(jsonSchema);
    }

    public static void validateSchema(String schemaPath, String json) throws Exception {
        schemaPath = String.format(TestConst.RESPONSE_SCHEMA_PATH, schemaPath);
        String schemaString = JsonConverter.convertJSONToString(schemaPath);
        Schema schema = SchemaLoader.load(new JSONObject(schemaString));
        try {
            schema.validate(JsonConverter.parseJSON(json));
        } catch (Exception ex) {
            List<String> errorList = ((ValidationException) ex).getAllMessages();
            throw new Exception(errorList.toString());
        }
    }

    public static <T> T read(String jsonObject, String path) {
        return (T) JSONPath.read(jsonObject, path);
    }
}
