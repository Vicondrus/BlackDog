package com.veterinary.controllers.admin_controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.controllers.DTOController;
import com.veterinary.dialogues.AlertBox;
import com.veterinary.dtos.DTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.services.RegularUserService;
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

    private RegularUserDTO regularUserDTO;

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

    public void update(){
        if(!password.getText().equals(confirm.getText())){
            AlertBox.display("ERROR", "Password mismatch");
        }else{
            regularUserService.update(regularUserDTO.getIdUser(), username.getText(), password.getText(), fullname.getText());

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
        regularUserDTO = (RegularUserDTO) dto;
    }
}
