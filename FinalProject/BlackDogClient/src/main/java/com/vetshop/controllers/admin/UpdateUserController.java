package com.vetshop.controllers.admin;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.DTOController;
import com.vetshop.controllers.user.InspectConsultationsController;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.DTO;
import com.vetshop.dtos.UserDTO;
import com.vetshop.services.RegularUserService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 * The type Update user controller.
 */
@Component
@FxmlView("updateuser-stage.fxml")
public class UpdateUserController implements DTOController {

    private final RegularUserService regularUserService;
    private UserDTO regularUserDTO;
    @FXML
    private TextField username;

    @FXML
    private TextField fullname;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirm;

    /**
     * Instantiates a new Update user controller.
     *
     * @param regularUserService the regular user service
     */
    public UpdateUserController(RegularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }

    /**
     * Update.
     */
    public void update() {
        if (!password.getText().equals(confirm.getText())) {
            AlertBox.display("ERROR", "Password mismatch");
        } else {
            regularUserService.postUpdateUser(regularUserDTO.getIdUser(), username.getText(), password.getText(), fullname.getText());

            Stage stage = (Stage) username.getScene().getWindow();
            stage.close();
            JavaFXApplication.changeScene(InspectUsersController.class);
        }
    }

    @Override
    public void refresh() {
        username.setText(regularUserDTO.getUsername());
        password.setText(regularUserDTO.getPassword());
        confirm.setText(regularUserDTO.getPassword());
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
