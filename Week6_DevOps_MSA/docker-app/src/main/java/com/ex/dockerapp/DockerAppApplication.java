package com.ex.dockerapp;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
public class DockerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerAppApplication.class, args);
    }

    @GetMapping
    public String message() throws UnknownHostException {
        return "Hello, Docker from " + Inet4Address.getLocalHost().getHostName();
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
        };
    }

}
