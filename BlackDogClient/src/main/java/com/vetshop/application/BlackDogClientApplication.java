package com.vetshop.application;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan( basePackages = {"com.vetshop"} )
public class BlackDogClientApplication {

	public static void main(String[] args) {
		Application.launch(JavaFXApplication.class,args);
	}

}
