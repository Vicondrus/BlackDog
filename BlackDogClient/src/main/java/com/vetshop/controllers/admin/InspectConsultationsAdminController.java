package com.vetshop.controllers.admin;

import com.itextpdf.text.DocumentException;
import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.services.implementations.ConsultationServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


/**
 * The type Inspect consultations admin controller.
 */
@Component
@FxmlView("inspectconsultationsadmin-stage.fxml")
public class InspectConsultationsAdminController implements Controller, Initializable {

    @Autowired
    private ConsultationServiceImpl consultationService;

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

    /**
     * Report.
     *
     * @param type the type
     */
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


    /**
     * Pdf report.
     */
    public void pdfReport(){
        report("PDF");
    }

    /**
     * Txt report.
     */
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

    /**
     * Back.
     */
    public void back() {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(AdminUserController.class);
    }
}
