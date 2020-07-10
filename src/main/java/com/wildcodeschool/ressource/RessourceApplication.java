package com.wildcodeschool.ressource;

import com.wildcodeschool.ressource.storage.StorageProperties;
import com.wildcodeschool.ressource.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class RessourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RessourceApplication.class, args);
	}

	/**
	 * The CommandLineRunner is a way to ask Spring Boot to do things at application startup
	 * The idea here is to create the storage directory if necessary
	 *
	 * @param storageService storage to initialize
	 * @return the CommandLineRunner bean
	 */
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

}
