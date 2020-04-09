package com.vetshop.controllers.admin;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.services.RegularUserService;
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
            AlertBox.display("ERROR", "Password mismatch");
        }else{
                regularUserService.postCreateUser(username.getText(), password.getText(), fullname.getText());

            Stage stage = (Stage) username.getScene().getWindow();
            stage.close();
            JavaFXApplication.changeScene(InspectUsersController.class);
        }
    }

}
