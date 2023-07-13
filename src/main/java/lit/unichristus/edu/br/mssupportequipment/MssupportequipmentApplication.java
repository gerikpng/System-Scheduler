package lit.unichristus.edu.br.mssupportequipment;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MssupportequipmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MssupportequipmentApplication.class, args);
	}

}
