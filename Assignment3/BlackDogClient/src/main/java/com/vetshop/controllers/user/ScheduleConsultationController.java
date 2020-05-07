package com.vetshop.controllers.user;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.AnimalDTO;
import com.vetshop.dtos.UserDTO;
import com.vetshop.exceptions.FieldException;
import com.vetshop.services.AnimalService;
import com.vetshop.services.ConsultationService;
import com.vetshop.services.RegularUserService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The type Schedule consultation controller.
 */
@Component
@FxmlView("scheduleconsultation-stage.fxml")
public class ScheduleConsultationController implements Controller, Initializable {

    private final RegularUserService regularUserService;

    private final AnimalService animalService;

    private final ConsultationService consultationService;

    @FXML
    private ComboBox<UserDTO> doctor;

    @FXML
    private ComboBox<AnimalDTO> patient;

    @FXML
    private DatePicker date;

    @FXML
    private TextField hour;

    @FXML
    private TextField minute;

    /**
     * Instantiates a new Schedule consultation controller.
     *
     * @param regularUserService  the regular user service
     * @param animalService       the animal service
     * @param consultationService the consultation service
     */
    public ScheduleConsultationController(RegularUserService regularUserService, AnimalService animalService, ConsultationService consultationService) {
        this.regularUserService = regularUserService;
        this.animalService = animalService;
        this.consultationService = consultationService;
    }

    /**
     * Schedule.
     */
    public void schedule() {
        UserDTO regularUserDTO = doctor.getValue();
        AnimalDTO animalDTO = patient.getValue();

        LocalDate localDate = date.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date d = Date.from(instant);

        try {
            consultationService.postScheduleConsultation(animalDTO, regularUserDTO, hour.getText(), minute.getText(), d);

            Stage stage = (Stage) minute.getScene().getWindow();
            stage.close();
            JavaFXApplication.changeScene(RegularUserController.class);
        } catch (FieldException e) {
            AlertBox.display("ERROR", e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<UserDTO> users = regularUserService.findAllRegularUsers();
        List<AnimalDTO> animals = animalService.findAllAnimals();

        doctor.setItems(FXCollections.observableList(users));
        doctor.getSelectionModel().selectFirst();

        doctor.setCellFactory(new Callback<ListView<UserDTO>, ListCell<UserDTO>>() {

            @Override
            public ListCell<UserDTO> call(ListView<UserDTO> l) {
                return new ListCell<UserDTO>() {
                    @Override
                    protected void updateItem(UserDTO item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getUsername());
                        }
                    }
                };
            }
        });

        doctor.setConverter(new StringConverter<UserDTO>() {
            @Override
            public String toString(UserDTO user) {
                if (user == null) {
                    return null;
                } else {
                    return user.getUsername() + "";
                }
            }

            @Override
            public UserDTO fromString(String userId) {
                return null;
            }
        });

        patient.setItems(FXCollections.observableList(animals));
        patient.getSelectionModel().selectFirst();

        patient.setCellFactory(new Callback<ListView<AnimalDTO>, ListCell<AnimalDTO>>() {

            @Override
            public ListCell<AnimalDTO> call(ListView<AnimalDTO> l) {
                return new ListCell<AnimalDTO>() {
                    @Override
                    protected void updateItem(AnimalDTO item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getName() + " - " + item.getOwner() + "'s " + item.getSpecies());
                        }
                    }
                };
            }
        });

        patient.setConverter(new StringConverter<AnimalDTO>() {
            @Override
            public String toString(AnimalDTO animal) {
                if (animal == null) {
                    return null;
                } else {
                    return animal.getName() + " - " + animal.getOwner() + "'s " + animal.getSpecies();
                }
            }

            @Override
            public AnimalDTO fromString(String animalId) {
                return null;
            }
        });

    }


    /**
     * Back.
     */
    public void back() {
        Stage stage = (Stage) doctor.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(RegularUserController.class);
    }
}
