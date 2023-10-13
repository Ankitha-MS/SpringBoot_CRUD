package org.jsp.student_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Ankitha",email = "sankitha092@gmail.com"),title = "Spring Boot CRUD", description = "Simple Rest API Using SpringBoot"))
public class StudentCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCrudApplication.class, args);
	}

}