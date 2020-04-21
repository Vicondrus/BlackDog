package com.vetshop.controllers.user;

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
 * The type Regular user controller.
 */
@Component
@FxmlView("regularuser-stage.fxml")
public class RegularUserController implements Controller {

    @FXML
    private Button button;

    private final AuthService authService;

    /**
     * Instantiates a new Regular user controller.
     *
     * @param authService the auth service
     */
    public RegularUserController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Get consultations window.
     */
    public void getConsultationsWindow(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectConsultationsController.class);
    }

    /**
     * Get animals window.
     */
    public void getAnimalsWindow(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectAnimalsController.class);
    }

    /**
     * Schedule consultation.
     */
    public void scheduleConsultation(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(ScheduleConsultationController.class);
    }

    /**
     * Logout.
     */
    public void logout(){
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
