package com.vetshop.controllers.user;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.DTOController;
import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.DTO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The type View consultation controller.
 */
@Component
@FxmlView("viewconsultation-stage.fxml")
public class ViewConsultationController implements DTOController {

    private ConsultationDTO consultationDTO;

    @FXML
    private TextField status;

    @FXML
    private TextField patient;

    @FXML
    private TextField doctor;

    @FXML
    private TextField diagnostic;

    @FXML
    private TextField details;

    @FXML
    private TextField recommendations;

    @FXML
    private DatePicker date;

    @FXML
    private TextField hour;

    @FXML
    private TextField minute;

    @Override
    public void setDTO(DTO dto) {
        consultationDTO = (ConsultationDTO) dto;
    }

    @Override
    public void refresh() {

        doctor.setText(consultationDTO.getDoctorName());
        patient.setText(consultationDTO.getAnimalName() + " - " + consultationDTO.getOwnerName() + "'s " + consultationDTO.getAnimalSpecies());

        date.setValue(consultationDTO.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(consultationDTO.getDate());
        hour.setText(calendar.get(Calendar.HOUR_OF_DAY) + "");
        minute.setText(calendar.get(Calendar.MINUTE) + "");

        details.setText(consultationDTO.getDetails());
        recommendations.setText(consultationDTO.getRecommendations());
        diagnostic.setText(consultationDTO.getDiagnostic());

        status.setText(consultationDTO.getStatusString());
    }

    /**
     * Back.
     */
    public void back() {
        Stage stage = (Stage) status.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectConsultationsController.class);
    }
}
