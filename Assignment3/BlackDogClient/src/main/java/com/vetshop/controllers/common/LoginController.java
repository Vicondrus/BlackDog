package com.vetshop.controllers.common;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.controllers.admin.AdminUserController;
import com.vetshop.controllers.user.RegularUserController;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.TypeDTO;
import com.vetshop.exceptions.InvalidCredentialsException;
import com.vetshop.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * The type Login controller.
 */
@Component
@FxmlView("main-stage.fxml")
public class LoginController implements Controller {

    private final AuthService authService;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    /**
     * Instantiates a new Login controller.
     *
     * @param authService the auth service
     */
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Login.
     */
    public void login() {
        TypeDTO type = null;
        try {
            type = authService.postLogin(username.getText(), password.getText());
            if (type.equals(TypeDTO.REGULAR))
                JavaFXApplication.changeScene(RegularUserController.class);
            else
                JavaFXApplication.changeScene(AdminUserController.class);
            Stage stage = (Stage) username.getScene().getWindow();
            stage.close();
        } catch (InvalidCredentialsException | IOException e) {
            AlertBox.display("ERROR", e.getMessage());
        }

    }

}
