package com.veterinary.controllers.admin_controllers;

import com.itextpdf.text.DocumentException;
import com.veterinary.controllers.Controller;
import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.services.ConsultationService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
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
    private TableColumn<ConsultationDTO, String> owner;

    @FXML
    private TableColumn<ConsultationDTO, String> doctor;

    @FXML
    private TableColumn<ConsultationDTO, String> patient;

    public void report(String type) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Report");
        File file = fileChooser.showSaveDialog(table.getScene().getWindow());
        if (file != null) {
            ConsultationDTO consultationDTO = table.getSelectionModel().getSelectedItem();
            try {
                consultationService.reportConsultation(consultationDTO.getConsultationId(), file.getPath(), type);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }


    public void pdfReport(){
        report("PDF");
    }

    public void txtReport(){
        report("TXT");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        date.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, Date>("date"));
        owner.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("owner"));
        patient.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("patient"));
        doctor.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("doctor"));

        table.getItems().setAll(consultationService.findAllForLoggedUser());
    }
}
