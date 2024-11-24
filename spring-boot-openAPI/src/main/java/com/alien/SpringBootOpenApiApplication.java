package com.alien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "COURSE SERVICE", version = "v 3.0", description = "Course API Crud operation"))
public class SpringBootOpenApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOpenApiApplication.class, args);
	}

}
