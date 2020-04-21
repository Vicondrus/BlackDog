package com.veterinary.application;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.veterinary.repositories")
@EntityScan( basePackages = {"com.veterinary.entities"} )
@ComponentScan( basePackages = {"com.veterinary"} )
public class VetShopApplication {

	public static void main(String[] args) {
		Application.launch(JavaFXApplication.class,args);
	}


}