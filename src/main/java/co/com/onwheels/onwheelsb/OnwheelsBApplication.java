package co.com.onwheels.onwheelsb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "OnWheels",
				version = "1.0.0",
				description = "Documentation created for OnWheels"
		),
		servers = {
				@Server(url = "http://localhost:8080/")})
				//@Server(url = "https://quisofka-mvn-b-production.up.railway.app/")
public class OnwheelsBApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnwheelsBApplication.class, args);
	}

}
