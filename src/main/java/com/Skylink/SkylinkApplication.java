package com.Skylink;

import Control.Scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SkylinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkylinkApplication.class, args);
	}

	@Bean
	Scheduler scheduler(){
		return new Scheduler();
	}
}
