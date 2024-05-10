package com.evan.categoryservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot REST API Documentation",
				description = "Spring Boot REST API Web Service",
				version = "v1.0",
				contact = @Contact(
						name = "Evan",
						email = "evan@gmail.com",
						url = "https://www.evan.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.license.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Sprig Boot Category Management Documentation",
				url = "https://www.externaldocumentation.category_service.html"
		)
)
@SpringBootApplication
public class CategoryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CategoryServiceApplication.class, args);
	}

}
