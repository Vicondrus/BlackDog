package com.veterinary.controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.dtos.AnimalDTO;
import com.veterinary.dtos.ConsultationDTO;
import com.veterinary.dtos.DTO;
import com.veterinary.dtos.RegularUserDTO;
import com.veterinary.services.AnimalService;
import com.veterinary.services.ConsultationService;
import com.veterinary.services.RegularUserService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
@FxmlView("updateconsultation-stage.fxml")
@Scope("singleton")
public class UpdateConsultationController implements DTOController {

    private ConsultationDTO consultationDTO;

    @Autowired
    private RegularUserService regularUserService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private ConsultationService consultationService;

    @FXML
    private ComboBox<AnimalDTO> patient;

    @FXML
    private ComboBox<RegularUserDTO> doctor;

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

    @FXML
    private Button updateButton;

    @Override
    public void setDTO(DTO dto) {
        consultationDTO = (ConsultationDTO) dto;
    }

    public void update(){
        RegularUserDTO regularUserDTO = doctor.getValue();
        AnimalDTO animalDTO = patient.getValue();

        LocalDate localDate = date.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date d = Date.from(instant);

        consultationService.update(consultationDTO.getConsultationId(),animalDTO.getAnimalId()+"",regularUserDTO.getIdUser()+"",diagnostic.getText(),details.getText(),recommendations.getText(),hour.getText(),minute.getText(),d);

        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(InspectConsultationsController.class);
    }

    @Override
    public void refresh() {
        List<RegularUserDTO> users = regularUserService.getAll();
        List<AnimalDTO> animals = animalService.getAll();

        doctor.setItems(FXCollections.observableList(users));
        doctor.getSelectionModel().selectFirst();

        doctor.setCellFactory(new Callback<ListView<RegularUserDTO>, ListCell<RegularUserDTO>>(){

            @Override
            public ListCell<RegularUserDTO> call(ListView<RegularUserDTO> l){
                return new ListCell<RegularUserDTO>(){
                    @Override
                    protected void updateItem(RegularUserDTO item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getUsername());
                        }
                    }
                } ;
            }
        });

        doctor.setConverter(new StringConverter<RegularUserDTO>() {
            @Override
            public String toString(RegularUserDTO user) {
                if (user == null){
                    return null;
                } else {
                    return user.getUsername()+"";
                }
            }

            @Override
            public RegularUserDTO fromString(String userId) {
                return null;
            }
        });

        patient.setItems(FXCollections.observableList(animals));
        patient.getSelectionModel().selectFirst();

        patient.setCellFactory(new Callback<ListView<AnimalDTO>, ListCell<AnimalDTO>>(){

            @Override
            public ListCell<AnimalDTO> call(ListView<AnimalDTO> l){
                return new ListCell<AnimalDTO>(){
                    @Override
                    protected void updateItem(AnimalDTO item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getName()+" of "+item.getOwner());
                        }
                    }
                } ;
            }
        });

        patient.setConverter(new StringConverter<AnimalDTO>() {
            @Override
            public String toString(AnimalDTO animal) {
                if (animal == null){
                    return null;
                } else {
                    return animal.getName()+" of "+animal.getOwner();
                }
            }

            @Override
            public AnimalDTO fromString(String animalId) {
                return null;
            }
        });

        doctor.setValue(consultationDTO.getDoctor());
        patient.setValue(consultationDTO.getAnimal());

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
