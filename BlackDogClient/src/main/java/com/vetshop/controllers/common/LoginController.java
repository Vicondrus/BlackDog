package com.vetshop.controllers.common;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.user.RegularUserController;
import com.vetshop.controllers.admin.AdminUserController;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.TypeDTO;
import com.vetshop.exceptions.InvalidCredentialsException;
import com.vetshop.services.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@FxmlView("main-stage.fxml")
public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @Autowired
    private LoginService loginService;

    public void login(){
        TypeDTO type = null;
        try {
            type = loginService.postLogin(username.getText(), password.getText());
            if(type.equals(TypeDTO.REGULAR))
                JavaFXApplication.changeScene(RegularUserController.class);
            else
                JavaFXApplication.changeScene(AdminUserController.class);
        } catch (InvalidCredentialsException | IOException e) {
            AlertBox.display("ERROR", e.getMessage());
        }

    }

}
