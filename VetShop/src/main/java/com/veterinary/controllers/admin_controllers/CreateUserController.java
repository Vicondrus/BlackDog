package com.veterinary.controllers.admin_controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.controllers.Controller;
import com.veterinary.controllers.common_controllers.InspectAnimalsController;
import com.veterinary.entities.UserType;
import com.veterinary.services.RegularUserService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("createuser-stage.fxml")
public class CreateUserController implements Controller {

    @Autowired
    private RegularUserService regularUserService;

    @FXML
    private TextField username;

    @FXML
    private TextField fullname;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirm;

    public void create(){
        if(!password.getText().equals(confirm.getText())){
            //show a popup
        }else{
            regularUserService.save(username.getText(), password.getText(), fullname.getText(), "REGULAR");

            Stage stage = (Stage) username.getScene().getWindow();
            stage.close();
            JavaFXApplication.changeScene(InspectUsersController.class);
        }
    }

}
