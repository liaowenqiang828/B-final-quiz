package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.persistence.Entity;

@SpringBootApplication
public class FinalQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalQuizApplication.class, args);
	}

}
