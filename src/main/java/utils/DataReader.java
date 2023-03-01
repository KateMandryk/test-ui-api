package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.Reader;

public class DataReader {
    private final String file = "src/main/resources/data.json";

    public String getValue(String name) {
        JSONParser parser = new JSONParser();
        Reader reader = null;
        Object jsonObj = null;

        try {
            reader = new java.io.FileReader(file);
            jsonObj = parser.parse(reader);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) jsonObj;
        String config = (String) jsonObject.get(name);

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}