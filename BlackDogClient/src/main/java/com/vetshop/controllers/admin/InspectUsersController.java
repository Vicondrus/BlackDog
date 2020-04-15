package com.vetshop.controllers.admin;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.controllers.user.InspectConsultationsController;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.UserDTO;
import com.vetshop.services.RegularUserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("inspectusers-stage.fxml")
public class InspectUsersController implements Controller, Initializable {

    @Autowired
    private RegularUserService regularUserService;

    @FXML
    private TableView<UserDTO> table;

    @FXML
    private TableColumn<UserDTO, String> username;

    @FXML
    private TableColumn<UserDTO, String> fullName;

    @Override
    public void refresh(){
        username.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("username"));
        fullName.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("fullName"));

        table.getItems().setAll(regularUserService.findAllRegularUsers());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    public void create(){
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(CreateUserController.class);
    }

    public void update(){
        UserDTO regularUserDTO = table.getSelectionModel().getSelectedItem();
        if(regularUserDTO == null){
            AlertBox.display("ERROR", "A user must be selected");
            return;
        }
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(UpdateUserController.class, regularUserDTO);
    }

    public void delete(){
        UserDTO regularUserDTO = table.getSelectionModel().getSelectedItem();
        if(regularUserDTO == null){
            AlertBox.display("ERROR", "A user must be selected");
            return;
        }
        regularUserService.deleteUser(regularUserDTO);
        refresh();
    }

    public void view(){
        UserDTO regularUserDTO = table.getSelectionModel().getSelectedItem();
        if(regularUserDTO == null){
            AlertBox.display("ERROR", "A user must be selected");
            return;
        }
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(ViewUserController.class, regularUserDTO);
    }

    public void back() {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(AdminUserController.class);
    }
}
