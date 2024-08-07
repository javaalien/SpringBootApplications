package com.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.swagger.config.ApplicationProperties;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableConfigurationProperties(ApplicationProperties.class)
public class SpringBootOpenapiSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOpenapiSwaggerApplication.class, args);
	}

}
