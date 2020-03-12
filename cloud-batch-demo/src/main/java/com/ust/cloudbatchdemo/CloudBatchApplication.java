package com.ust.cloudbatchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
//@EnableBatchProcessing
public class CloudBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudBatchApplication.class, args);
	}

}
