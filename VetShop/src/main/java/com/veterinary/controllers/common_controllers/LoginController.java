package com.veterinary.controllers.common_controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.controllers.Controller;
import com.veterinary.controllers.admin_controllers.AdminUserController;
import com.veterinary.controllers.user_controllers.RegularUserController;
import com.veterinary.dtos.TypeDTO;
import com.veterinary.services.LoginService;
import com.veterinary.services.exceptions.InvalidCredentialsException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("main-stage.fxml")
public class LoginController implements Controller {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @Autowired
    private LoginService loginService;

    public void login() {
        try {
            TypeDTO type = loginService.loginUser(username.getText(),password.getText());
            if(type.equals(TypeDTO.REGULAR))
                JavaFXApplication.changeScene(RegularUserController.class);
            else
                JavaFXApplication.changeScene(AdminUserController.class);
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();
        }
    }

}
