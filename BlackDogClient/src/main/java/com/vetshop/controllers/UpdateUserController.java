package com.vetshop.controllers;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.DTO;
import com.vetshop.dtos.UserDTO;
import com.vetshop.services.RegularUserService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("updateuser-stage.fxml")
public class UpdateUserController implements DTOController {

    private UserDTO regularUserDTO;

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

    public void update() {
        if(!password.getText().equals(confirm.getText())){
            AlertBox.display("ERROR", "Password mismatch");
        }else{

            Stage stage = (Stage) username.getScene().getWindow();
            stage.close();
            JavaFXApplication.changeScene(InspectUsersController.class);
        }
    }

    @Override
    public void refresh(){
        username.setText(regularUserDTO.getUsername());
        password.setText(regularUserDTO.getPassword());
        confirm.setText(regularUserDTO.getPassword());
        fullname.setText(regularUserDTO.getFullName());
    }


    @Override
    public void setDTO(DTO dto) {
        regularUserDTO = (UserDTO) dto;
    }
}
