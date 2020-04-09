package com.vetshop.controllers.admin;

import com.itextpdf.text.DocumentException;
import com.vetshop.controllers.Controller;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.services.ConsultationService;
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
@FxmlView("inspectconsultationsadmin-stage.fxml")
public class InspectConsultationsAdminController implements Controller, Initializable {

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
        ConsultationDTO consultationDTO = table.getSelectionModel().getSelectedItem();
        if(consultationDTO == null) {
            AlertBox.display("ERROR", "A consultation must be selected");
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Report");
        File file = fileChooser.showSaveDialog(table.getScene().getWindow());
        if (file != null) {
            try {
                consultationService.reportConsultation(consultationDTO, file.getPath(), type);
            } catch (IOException | DocumentException e) {
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
        owner.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("ownerName"));
        patient.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("animalName"));
        doctor.setCellValueFactory(new PropertyValueFactory<ConsultationDTO, String>("doctorName"));

        table.getItems().setAll(consultationService.postFindAllForLoggedUser());
    }
}
