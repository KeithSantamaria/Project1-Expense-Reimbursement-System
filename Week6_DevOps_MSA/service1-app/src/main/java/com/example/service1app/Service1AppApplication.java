package com.example.service1app;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Service1AppApplication {

    private final MongoClient mongo;

    @Autowired
    public Service1AppApplication(MongoClient mongo) {
        this.mongo = mongo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Service1AppApplication.class, args);
    }



}
