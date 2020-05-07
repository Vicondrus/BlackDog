package com.vetshop.controllers.admin;


import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.controllers.common.InspectAnimalsController;
import com.vetshop.controllers.common.LoginController;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * The type Admin user controller.
 */
@Component
@FxmlView("adminuser-stage.fxml")
public class AdminUserController implements Controller {

    private final AuthService authService;

    @FXML
    private Button button;

    /**
     * Instantiates a new Admin user controller.
     *
     * @param authService the auth service
     */
    public AdminUserController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Get users window.
     */
    public void getUsersWindow() {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectUsersController.class);
    }

    /**
     * Get animals window.
     */
    public void getAnimalsWindow() {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectAnimalsController.class);
    }

    /**
     * Get consultations window.
     */
    public void getConsultationsWindow() {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectConsultationsAdminController.class);
    }

    /**
     * Log out.
     */
    public void logOut() {
        try {
            authService.logOut();
        } catch (IOException e) {
            AlertBox.display("ERROR", e.getMessage());
        }

        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(LoginController.class);
    }
}
