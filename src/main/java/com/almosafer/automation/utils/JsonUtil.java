/**
 * Project Almosafer
 *
 * @author Nandhini
 */
package com.almosafer.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;

import java.io.*;

/**
 * @author Nandhini
 */
public class JsonUtil {
    @DataProvider(name = "getData")
    public Object[][] readJson(Class className) throws FileNotFoundException {
        JsonParser jsonParser = new JsonParser();
        Object obj = jsonParser.parse("");
        try {
            readJsonAndConvertIntoRequiredClassType((JsonObject) obj,className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * pass JSON Data
     * @return required class type
     */
    public Object readJsonAndConvertIntoRequiredClassType(JsonObject jsonObject, Class classType) throws IOException {
        System.out.println(new ObjectMapper().readValue(jsonObject.getAsString(), classType));
        return new ObjectMapper().readValue(jsonObject.getAsString(), classType);
    }

}
