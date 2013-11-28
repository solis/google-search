package com.exadel.dev.searchclient;

import com.exadel.dev.searchservice.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:33 AM, 2/11/13
 */

public class SearchClient {
    public static void main(String[] args) throws IOException {
        SearchService searchService = new SearchService();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("search(q=quit): ");
        String query = bufferedReader.readLine();
        while (!query.equals("q")) {
            System.out.println(searchService.search(query.replaceAll(" ", "+")));
            System.out.println("search(q=quit): ");
            query = bufferedReader.readLine();
        }
    }
}
