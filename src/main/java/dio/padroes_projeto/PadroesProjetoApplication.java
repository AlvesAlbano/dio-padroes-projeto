package dio.padroes_projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PadroesProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadroesProjetoApplication.class, args);
		System.out.println("http://localhost:8080/");
		System.out.println("http://localhost:8080/swagger-ui.html");
	}
}
