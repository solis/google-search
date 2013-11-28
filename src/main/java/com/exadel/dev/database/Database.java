package com.exadel.dev.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.String;import java.lang.System;import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:35 AM, 2/11/13
 */

public class Database {
    private final static String DATABASE_FILE = "database.properties";
    private HashMap<String, String> db;

    public Database() throws IOException {
        db = new HashMap<String, String>();
        loadDatabaseFromFile();
    }

    public void put(String key, String value) throws IOException {
        db.put(key, value);
        saveDatabaseToFile();
    }

    public boolean contains(String key) {
        return db.containsKey(key);
    }

    public String get(String key) {
        return db.get(key);
    }

    private void saveDatabaseToFile() throws IOException {
        Properties properties = new Properties();

        for (Map.Entry<String, String> entry : db.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }
        properties.store(new FileOutputStream(DATABASE_FILE), null);
    }

    private void loadDatabaseFromFile() throws IOException {
        Properties properties = new Properties();
        File file = new File(DATABASE_FILE);
        if (file.exists() && file.canRead()) {
            properties.load(new FileInputStream(file));
        }


        for (String key : properties.stringPropertyNames()) {
            db.put(key, properties.get(key).toString());
        }
    }
}
