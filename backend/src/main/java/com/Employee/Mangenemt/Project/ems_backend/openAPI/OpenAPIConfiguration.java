package com.Employee.Mangenemt.Project.ems_backend.openAPI;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenApi(){
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact contact = new Contact();
        contact.setName("Arun");
        contact.setEmail("arunbairav12@gmail.com");

        Info info = new Info();
        info.setTitle("Employee Management System API");
        info.version("1.0");
        info.setDescription("This API exposes all end points to manage employees");
        info.setContact(contact);

        OpenAPI openApiSetup = new OpenAPI();
        openApiSetup.info(info).servers(List.of(server));
        return openApiSetup;
    }

}
