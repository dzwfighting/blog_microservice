package com.evan.blogpost_service;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Post Service - Spring Boot REST API Documentation",
				description = "Use Spring Boot to achieve CRUD functions",
				version = "v1.0",
				contact = @Contact(
						name = "Evan",
						email = "evan@gmail.com",
						url = "https://www.evan.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.evanlicense.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User Management Documentation",
				url = "https://evan/externaldocumentation.com"
		)
)
@SpringBootApplication
public class BlogpostServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogpostServiceApplication.class, args);
	}

}
