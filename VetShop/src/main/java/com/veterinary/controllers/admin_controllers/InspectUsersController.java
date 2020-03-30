package com.veterinary.controllers.admin_controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.controllers.Controller;
import com.veterinary.controllers.common_controllers.CreateAnimalController;
import com.veterinary.controllers.user_controllers.CreateConsultationController;
import com.veterinary.controllers.user_controllers.UpdateConsultationController;
import com.veterinary.controllers.user_controllers.ViewConsultationController;
import com.veterinary.dialogues.AlertBox;
import com.veterinary.dtos.AnimalDTO;
import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.services.ConsultationService;
import com.veterinary.services.RegularUserService;
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
import java.util.Date;
import java.util.ResourceBundle;

@Component
@FxmlView("inspectusers-stage.fxml")
public class InspectUsersController implements Controller, Initializable {

    @Autowired
    private RegularUserService regularUserService;

    @FXML
    private TableView<RegularUserDTO> table;

    @FXML
    private TableColumn<RegularUserDTO, String> username;

    @FXML
    private TableColumn<RegularUserDTO, String> fullName;

    @Override
    public void refresh(){
        username.setCellValueFactory(new PropertyValueFactory<RegularUserDTO, String>("username"));
        fullName.setCellValueFactory(new PropertyValueFactory<RegularUserDTO, String>("fullName"));

        table.getItems().setAll(regularUserService.getAll());
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
        RegularUserDTO regularUserDTO = table.getSelectionModel().getSelectedItem();
        if(regularUserDTO == null){
            AlertBox.display("ERROR", "A user must be selected");
            return;
        }
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(UpdateUserController.class, regularUserDTO);
    }

    public void delete(){
        RegularUserDTO regularUserDTO = table.getSelectionModel().getSelectedItem();
        if(regularUserDTO == null){
            AlertBox.display("ERROR", "A user must be selected");
            return;
        }
        regularUserService.delete(regularUserDTO.getIdUser());
        refresh();
    }

    public void view(){
        RegularUserDTO regularUserDTO = table.getSelectionModel().getSelectedItem();
        if(regularUserDTO == null){
            AlertBox.display("ERROR", "A user must be selected");
            return;
        }
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(ViewUserController.class, regularUserDTO);
    }

}
