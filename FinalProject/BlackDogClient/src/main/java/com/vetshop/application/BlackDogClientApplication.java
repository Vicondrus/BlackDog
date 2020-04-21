package com.vetshop.application;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Black dog client application.
 */
@SpringBootApplication
@ComponentScan( basePackages = {"com.vetshop"} )
public class BlackDogClientApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
		Application.launch(JavaFXApplication.class,args);
	}

}
