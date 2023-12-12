package com.example.FATAS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

@EntityScan("com.example.FATAS.entities")
public class FatasApplication {

	public static void main(String[] args) {
		SpringApplication.run(FatasApplication.class, args);
	}

}
