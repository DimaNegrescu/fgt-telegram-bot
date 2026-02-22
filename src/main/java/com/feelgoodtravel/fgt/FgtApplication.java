package com.feelgoodtravel.fgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class FgtApplication {

	public static void main(String[] args) {
		SpringApplication.run(FgtApplication.class, args);
	}

}
