package com.vetshop.controllers.admin;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.DTOController;
import com.vetshop.controllers.user.InspectConsultationsController;
import com.vetshop.dtos.DTO;
import com.vetshop.dtos.UserDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 * The type View user controller.
 */
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

    /**
     * Back.
     */
    public void back() {
        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectConsultationsController.class);
    }
}
