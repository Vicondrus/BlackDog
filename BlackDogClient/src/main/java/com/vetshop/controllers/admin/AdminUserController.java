package com.vetshop.controllers.admin;


import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.controllers.common.InspectAnimalsController;
import com.vetshop.controllers.common.LoginController;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@FxmlView("adminuser-stage.fxml")
public class AdminUserController implements Controller {

    @Autowired
    private AuthService authService;

    @FXML
    private Button button;

    public void getUsersWindow(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectUsersController.class);
    }

    public void getAnimalsWindow(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectAnimalsController.class);
    }

    public void getConsultationsWindow(){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectConsultationsAdminController.class);
    }

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
