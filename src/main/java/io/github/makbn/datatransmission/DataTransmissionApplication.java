package io.github.makbn.datatransmission;

import com.netflix.discovery.EurekaClient;
import io.github.makbn.datatransmission.common.SerialCommHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Mehdi Akbarian-Rastaghi
 * Std. No. : 9312430437
 */
@SpringBootApplication(
		exclude = {RepositoryRestMvcAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class})
@EnableEurekaClient
public class DataTransmissionApplication {


    @Qualifier("eurekaClient")
    @Autowired
    @Lazy
    private static EurekaClient eurekaClient;

	public static void main(String[] args) {
		SpringApplication.run(DataTransmissionApplication.class, args);
		SerialCommHelper.init();

        System.out.println(eurekaClient.getApplication("client-service").getName());
	}

    @LoadBalanced //adding this line solved the issue
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
