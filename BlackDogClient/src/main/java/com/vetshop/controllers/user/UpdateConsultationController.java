package com.vetshop.controllers.user;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.DTOController;
import com.vetshop.dtos.AnimalDTO;
import com.vetshop.dtos.ConsultationDTO;
import com.vetshop.dtos.DTO;
import com.vetshop.dtos.UserDTO;
import com.vetshop.services.AnimalService;
import com.vetshop.services.ConsultationService;
import com.vetshop.services.RegularUserService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
    private ComboBox<UserDTO> doctor;

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
        UserDTO regularUserDTO = doctor.getValue();
        AnimalDTO animalDTO = patient.getValue();

        LocalDate localDate = date.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date d = Date.from(instant);

        consultationService.postUpdateConsultation(consultationDTO.getConsultationId(),animalDTO,regularUserDTO,diagnostic.getText(),details.getText(),recommendations.getText(),hour.getText(),minute.getText(),d);

        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(InspectConsultationsController.class);
    }

    @Override
    public void refresh() {
        List<UserDTO> users = regularUserService.findAllRegularUsers();
        List<AnimalDTO> animals = animalService.findAllAnimals();

        doctor.setItems(FXCollections.observableList(users));
        doctor.getSelectionModel().selectFirst();

        doctor.setCellFactory(new Callback<ListView<UserDTO>, ListCell<UserDTO>>(){

            @Override
            public ListCell<UserDTO> call(ListView<UserDTO> l){
                return new ListCell<UserDTO>(){
                    @Override
                    protected void updateItem(UserDTO item, boolean empty) {
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

        doctor.setConverter(new StringConverter<UserDTO>() {
            @Override
            public String toString(UserDTO user) {
                if (user == null){
                    return null;
                } else {
                    return user.getUsername()+"";
                }
            }

            @Override
            public UserDTO fromString(String userId) {
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
                            setText(item.getName()+ " - " +item.getOwner()+"'s " + item.getSpecies());
                        }
                    }
                } ;
            }
        });

        patient.setConverter(new StringConverter<AnimalDTO>() {
            @Override
            public String toString(AnimalDTO item) {
                if (item == null){
                    return null;
                } else {
                    return item.getName()+ " - " +item.getOwner()+"'s " + item.getSpecies();
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
