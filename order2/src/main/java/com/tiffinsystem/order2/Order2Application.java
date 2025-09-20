package com.tiffinsystem.order2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Order2Application {

	public static void main(String[] args) {
		SpringApplication.run(Order2Application.class, args);
	}

}
