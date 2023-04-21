package com.poc.app.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.app.constant.TestConst;
import com.poc.app.models.APIData;
import com.poc.app.models.APIDataPoc;
import com.poc.app.models.UserInfo;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;

public class Helper {

    private Helper() {
        throw new IllegalStateException("Helper class");
    }

    public static Optional<APIData> getAPIInfo(String api) {
        // JSON file to Java object
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            File file = new File(classLoader.getResource(TestConst.API_DATA_PATH).getFile());
            List<APIData> users = mapper.readValue(parseJsonObjectToString(file), new TypeReference<List<APIData>>() {});
            return users.stream().filter(x -> x.getApi().equalsIgnoreCase(api)).findAny();
        } catch (JsonParseException e) {
            throw new IllegalArgumentException("Unable to parse json '" + TestConst.API_DATA_PATH + "'.", e);
        } catch (JsonMappingException e) {
            throw new IllegalArgumentException("Unable to map json '" + TestConst.API_DATA_PATH + "' to model.", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read the file '" + TestConst.API_DATA_PATH + "'.", e);
        } catch (Exception e) {
            throw new IllegalArgumentException("The file '" + TestConst.API_DATA_PATH + "' not found.", e);
        }
    }

    public static String removePhoneNumberFormat(String phoneNumber) {
        if(phoneNumber == null)
            return null;
        return phoneNumber.replace("-","");
    }

    public static String parseJsonObjectToString(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<JSONObject> jsonUserArray;
        jsonUserArray = JsonPath.with(file).getJsonObject(getEnvironment());
        return mapper.writeValueAsString(jsonUserArray);
    }

    public static String getEnvironment(){
        String env = System.getProperty("env");
        if(env==null)
            return "SIT";
        else
            return env.toUpperCase();
    }

    public static Optional<UserInfo> getUserPoc(String username) {
        // JSON file to Java object
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            File file = new File(classLoader.getResource(TestConst.USER_DATA_PATH).getFile());
            List<UserInfo> users = mapper.readValue(parseJsonObjectToString(file), new TypeReference<List<UserInfo>>() {});
            return users.stream().filter(x -> x.getUsername().equalsIgnoreCase(username)).findAny();
        } catch (JsonParseException e) {
            throw new IllegalArgumentException("Unable to parse json '" + TestConst.USER_DATA_PATH + "'.", e);
        } catch (JsonMappingException e) {
            throw new IllegalArgumentException("Unable to map json '" + TestConst.USER_DATA_PATH + "' to model.", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read the file '" + TestConst.USER_DATA_PATH + "'.", e);
        } catch (Exception e) {
            throw new IllegalArgumentException("The file '" + TestConst.USER_DATA_PATH + "' not found.", e);
        }
    }

    public static Optional<APIDataPoc> getAPIInfoPoc(String api) {
        // JSON file to Java object
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            File file = new File(classLoader.getResource(TestConst.API_DATA_PATH).getFile());
            List<APIDataPoc> users = mapper.readValue(parseJsonObjectToString(file), new TypeReference<List<APIDataPoc>>() {});
            return users.stream().filter(x -> x.getApi().equalsIgnoreCase(api)).findAny();
        } catch (JsonParseException e) {
            throw new IllegalArgumentException("Unable to parse json '" + TestConst.API_DATA_PATH + "'.", e);
        } catch (JsonMappingException e) {
            throw new IllegalArgumentException("Unable to map json '" + TestConst.API_DATA_PATH + "' to model.", e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read the file '" + TestConst.API_DATA_PATH + "'.", e);
        } catch (Exception e) {
            throw new IllegalArgumentException("The file '" + TestConst.API_DATA_PATH + "' not found.", e);
        }
    }


}
