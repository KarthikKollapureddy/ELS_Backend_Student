package com.Student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.Trainer.model","com.Student","com.Elearning.model","com.Admin"})
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}
	
	//@LoadBalanced
	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	

}
