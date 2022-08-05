package net.javaguides.springboot;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringbootBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){

		return new RestTemplate();
	}

}
