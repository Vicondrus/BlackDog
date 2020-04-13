package com.vetshop.controllers.user;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.services.ConsultationService;
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
import java.util.List;
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

    @FXML
    private TableColumn<ConsultationDTO, String> status;

    @Override
    public void refresh(){
        date.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, Date>("date"));
        animal.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("animalName"));
        recommendations.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("recommendations"));
        diagnostic.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("diagnostic"));
        details.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("details"));
        status.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("statusString"));

        List<ConsultationDTO> list = consultationService.postFindAllForLoggedUser();

        table.getItems().setAll(list);
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
        consultationService.deleteConsultation(consultationDTO);
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
