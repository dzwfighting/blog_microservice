package com.evan.commentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot REST API Documentation",
				description = "Spring Boot REST API - CRUD",
				version = "v1.0",
				contact = @Contact(
						name = "Evan",
						email = "evan@gmail.com",
						url = "https://www.evan.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.livence.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Comment Management Documentation",
				url = "https://www.externaldocumentation/comment_management.html"
		)
)
@SpringBootApplication
@EnableDiscoveryClient
public class CommentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentServiceApplication.class, args);
	}

}
