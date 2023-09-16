package link.grooverdev.web.api.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(title = "API SWAGGER User Application", version = "1.0", description = "API REST Documentation for the User Application version 1.0"))
@SpringBootApplication
public class BackDemoDockerizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackDemoDockerizeApplication.class, args);
	}

}
