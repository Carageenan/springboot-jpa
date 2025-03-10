package com.jpa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IhsanTryJpaApplication {

	public static void main(String[] args) {
		System.out.println("coba ini ah");
		SpringApplication.run(IhsanTryJpaApplication.class, args);
	}
}
