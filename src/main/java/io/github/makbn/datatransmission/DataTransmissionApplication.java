package io.github.makbn.datatransmission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Mehdi Akbarian-Rastaghi
 * Std. No. : 9312430437
 */
@SpringBootApplication(
		exclude = {RepositoryRestMvcAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class})
public class DataTransmissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataTransmissionApplication.class, args);
	}

}

