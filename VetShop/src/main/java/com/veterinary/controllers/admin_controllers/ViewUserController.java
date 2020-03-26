package com.veterinary.controllers.admin_controllers;

import com.veterinary.controllers.DTOController;
import com.veterinary.dtos.DTO;
import com.veterinary.dtos.RegularUserDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("viewuser-stage.fxml")
public class ViewUserController implements DTOController {

    private RegularUserDTO regularUserDTO;

    @FXML
    private TextField username;

    @FXML
    private TextField fullname;

    @FXML
    private TextField password;

    @Override
    public void refresh(){
        username.setText(regularUserDTO.getUsername());
        password.setText(regularUserDTO.getPassword());
        fullname.setText(regularUserDTO.getFullName());
    }

    @Override
    public void setDTO(DTO dto) {
        regularUserDTO = (RegularUserDTO) dto;
    }

}
