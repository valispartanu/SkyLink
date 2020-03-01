package com.Skylink;

import Control.Admin;
import Control.Scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class SkylinkApplication {

	@Scheduled
	public static void main(String[] args) {
		SpringApplication.run(SkylinkApplication.class, args);
	}

	@Bean
	Scheduler scheduler(){
		return new Scheduler();
	}
//
//	@Bean
//	Admin admin(){
//		return Admin.getInstance();
//	}

}
