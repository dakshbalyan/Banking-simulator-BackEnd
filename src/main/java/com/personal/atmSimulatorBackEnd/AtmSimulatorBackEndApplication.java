package com.personal.atmSimulatorBackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtmSimulatorBackEndApplication {

	public static void main(String[] args) {
//		System.setProperty("server.servlet.context-path", "/ATM");
		SpringApplication.run(AtmSimulatorBackEndApplication.class, args);
	}

}
