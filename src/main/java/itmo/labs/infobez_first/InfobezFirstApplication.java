package itmo.labs.infobez_first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class InfobezFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfobezFirstApplication.class, args);
	}

}
