package com.veterinary.controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.entities.RegularUser;
import com.veterinary.services.LoginService;
import com.veterinary.services.exceptions.InvalidCredentialsException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("main-stage.fxml")
public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @Autowired
    private LoginService loginService;

    public void login() {
        try {
            loginService.loginUser(username.getText(),password.getText());
            JavaFXApplication.changeScene(RegularUserController.class);
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();
        }
    }

}
