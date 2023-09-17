package com.revealtest.revealtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RevealtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevealtestApplication.class, args);
	}

}
