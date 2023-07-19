package lit.unichristus.edu.br.mssupportequipments;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableFeignClients
@EnableScheduling
public class MsSupportEquipmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSupportEquipmentsApplication.class, args);
	}

}
