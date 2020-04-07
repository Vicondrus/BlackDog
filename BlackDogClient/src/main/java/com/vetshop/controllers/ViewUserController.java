package com.vetshop.controllers;

import com.vetshop.dtos.DTO;
import com.vetshop.dtos.UserDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("viewuser-stage.fxml")
public class ViewUserController implements DTOController {

    private UserDTO regularUserDTO;

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
        regularUserDTO = (UserDTO) dto;
    }

}
