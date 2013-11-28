package com.exadel.dev.searchservice;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:43 AM, 2/12/13
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.exadel.dev.database.*;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:29 AM, 2/11/13
 */

public class SearchService {
    private final static String SEARCH_URL = "https://www.googleapis.com/customsearch/v1?";
    private final static String KEY = "AIzaSyB_rDAcZWjwfYd8QD7oSHHi7GkQUlwfDxg";
    private final static String CX = "017576662512468239146:omuauf_lfve";
    private Database Cache;

    public SearchService() throws IOException {
        Cache = new Database();
    }

    public String search(String searchRequest) throws IOException {
        String searchResponse;
        if (Cache.contains(searchRequest)) {
            searchResponse = Cache.get(searchRequest);
        } else {
            searchResponse = googleRequest(searchRequest);
            Cache.put(searchRequest, searchResponse);
        }

        return searchResponse;
    }

    private String googleRequest(String searchRequest) throws IOException {
        String result = "";
        try {
            URL url = new URL(SEARCH_URL + "key=" + KEY + "&cx=" + CX + "&q=" + searchRequest);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            if (connection.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.contains("\"snippet\": \"")) {
                        String snippet = inputLine.substring(inputLine.indexOf("\"snippet\": \"") + ("\"snippet\": \"").length(), inputLine.indexOf("\","));
                        result += snippet + "\n";
                    }
                }
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }
}
