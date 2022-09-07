package com.almosafer.automation.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static JSONObject parse(String file) {
        InputStream is = JsonParser.class.getClassLoader().getResourceAsStream(file);
        assert is != null;
        return new JSONObject(new JSONTokener(is));
    }

    public static String parseJsonObjToStr (JSONObject jsonObject, String jsonObjKey, String key){
        return jsonObject.getJSONObject(jsonObjKey).getString(key);
    }

    public static String[] parseJsonArrToStringArr(JSONObject jsonObject, String jsonObjKey,String jsonArrKey){
        JSONArray jsonArray = jsonObject.getJSONObject(jsonObjKey).getJSONArray(jsonArrKey);
        List<String> list = new ArrayList<String>();
        for(int i=0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        String[] stringArray = list.toArray(new String[list.size()]);
        return stringArray;
    }
}
