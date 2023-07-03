package com.example.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application {

	static Logger logger = LoggerFactory.getLogger("com.example.demo2");
	
	public static void main(String[] args) {
		logger.error("Eine Fehler ausgabe");
		SpringApplication.run(Demo2Application.class, args);
	}

}
