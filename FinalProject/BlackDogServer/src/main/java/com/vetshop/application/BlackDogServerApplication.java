package com.vetshop.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

/**
 * The type Black dog server application.
 */
@SpringBootApplication
@EnableJpaRepositories("com.vetshop.repositories")
@EntityScan(basePackages = {"com.vetshop.entities"})
@ComponentScan(basePackages = {"com.vetshop"})
public class BlackDogServerApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BlackDogServerApplication.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println("BEAN :: " + beanName);
        }
    }

}
