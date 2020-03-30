package com.veterinary.controllers.user_controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.controllers.Controller;
import com.veterinary.dialogues.AlertBox;
import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.services.ConsultationService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Component
@FxmlView("inspectconsultations-stage.fxml")
public class InspectConsultationsController implements Controller, Initializable {

    @Autowired
    private ConsultationService consultationService;

    @FXML
    private TableView<ConsultationDTO> table;

    @FXML
    private TableColumn<ConsultationDTO, Date> date;

    @FXML
    private TableColumn<ConsultationDTO, String> animal;

    @FXML
    private TableColumn<ConsultationDTO, String> diagnostic;

    @FXML
    private TableColumn<ConsultationDTO, String> recommendations;

    @FXML
    private TableColumn<ConsultationDTO, String> details;

    @Override
    public void refresh(){
        date.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, Date>("date"));
        animal.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("animal"));
        recommendations.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("recommendations"));
        diagnostic.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("diagnostic"));
        details.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("details"));

        table.getItems().setAll(consultationService.findAllForLoggedUser());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    public void create(){
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(CreateConsultationController.class);
    }

    public void update(){
        ConsultationDTO consultationDTO = table.getSelectionModel().getSelectedItem();
        if(consultationDTO == null){
            AlertBox.display("ERROR", "A consultation must be selected");
            return;
        }
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(UpdateConsultationController.class, consultationDTO);
    }

    public void delete(){
        ConsultationDTO consultationDTO = table.getSelectionModel().getSelectedItem();
        if(consultationDTO == null){
            AlertBox.display("ERROR", "A consultation must be selected");
            return;
        }
        consultationService.delete(consultationDTO.getConsultationId());
        refresh();
    }

    public void view(){
        ConsultationDTO consultationDTO = table.getSelectionModel().getSelectedItem();
        if(consultationDTO == null){
            AlertBox.display("ERROR", "A consultation must be selected");
            return;
        }
        JavaFXApplication.changeScene(ViewConsultationController.class,consultationDTO);
    }
}
