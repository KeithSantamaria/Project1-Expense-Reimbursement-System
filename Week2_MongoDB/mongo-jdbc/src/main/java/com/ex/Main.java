package com.ex;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;

public class Main {
    public static void main(String[] args) {
        try {
            ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/paintstore");
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .retryWrites(true)
                    .build();
            MongoClient client = MongoClients.create(settings);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
