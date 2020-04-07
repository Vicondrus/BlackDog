package com.vetshop.controllers;

import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.DTO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
@FxmlView("viewconsultation-stage.fxml")
public class ViewConsultationController implements DTOController {

    private ConsultationDTO consultationDTO;

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
        consultationDTO= (ConsultationDTO) dto;
    }

    @Override
    public void refresh() {

        doctor.setText(consultationDTO.getDoctorName());
        patient.setText(consultationDTO.getAnimalName() + " of " + consultationDTO.getOwnerName());

        date.setValue(consultationDTO.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(consultationDTO.getDate());
        hour.setText(calendar.get(Calendar.HOUR_OF_DAY)+"");
        minute.setText(calendar.get(Calendar.MINUTE)+"");

        details.setText(consultationDTO.getDetails());
        recommendations.setText(consultationDTO.getRecommendations());
        diagnostic.setText(consultationDTO.getDiagnostic());

    }
}
