/*
 *Purpose : Class is implemented for reading the data from json file
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 10-07-2021
 */
package com.makemytripapplication.utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class JsonUtil {

    /**
     * readJson method is used to read the data from json file
     * @param filePath location of a file
     * @return arr
     */
    public String[][] readJson(String filePath,int noOfInputs) {
        File file = new File(filePath);
        FileReader reader;
        JSONObject obj = null;
        try {
            reader = new FileReader(file);
            obj = (JSONObject)new JSONParser().parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JSONArray userData = (JSONArray) Objects.requireNonNull(obj).get("userData");

        String[][] arr = new String[userData.size()][noOfInputs];

        for (int i=0; i <userData.size(); i++) {
                JSONObject usersData = (JSONObject) userData.get(i);

                arr[i][0] = String.valueOf(usersData.get("type"));
                arr[i][1]= String.valueOf(usersData.get("mobile number"));
                arr[i][2] = String.valueOf(usersData.get("country"));
                arr[i][3] = String.valueOf(usersData.get("otpOrPassword"));
                arr[i][4] = String.valueOf(usersData.get("password"));
        }

        return arr;
    }
}
