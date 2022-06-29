package com.dmitry.NewsClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class NewsClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsClientApplication.class, args);
	}

}
