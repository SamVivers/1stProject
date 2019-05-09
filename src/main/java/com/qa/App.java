package com.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.qa.repository.UserRepository;

@SpringBootApplication

public class App 
{
    public static void main(String[] args) {
    	SpringApplication.run(App.class, args);
    }
}
