package com.gmoi.shortify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ShortifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortifyApplication.class, args);
	}

}
