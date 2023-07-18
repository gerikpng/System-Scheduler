package lit.unichristus.edu.br.mssupportequipments;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableRabbit
@EnableFeignClients
public class MsSupportEquipmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSupportEquipmentsApplication.class, args);
	}

}
