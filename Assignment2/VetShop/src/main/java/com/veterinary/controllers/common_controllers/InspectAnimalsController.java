package com.veterinary.controllers.common_controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.controllers.Controller;
import com.veterinary.dialogues.AlertBox;
import com.veterinary.dtos.AnimalDTO;
import com.veterinary.services.AnimalService;
import com.veterinary.services.exceptions.NoSuchEntityException;
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
import java.util.ResourceBundle;

@Component
@FxmlView("inspectanimals-stage.fxml")
public class InspectAnimalsController implements Controller, Initializable {

    @Autowired
    private AnimalService animalService;

    @FXML
    private TableView<AnimalDTO> table;

    @FXML
    private TableColumn<AnimalDTO, String> name;

    @FXML
    private TableColumn<AnimalDTO, String> owner;

    @FXML
    private TableColumn<AnimalDTO, String> species;

    @Override
    public void refresh(){
        name.setCellValueFactory(new PropertyValueFactory<AnimalDTO, String>("name"));
        owner.setCellValueFactory(new PropertyValueFactory<AnimalDTO, String>("owner"));
        species.setCellValueFactory(new PropertyValueFactory<AnimalDTO, String>("species"));

        try {
            table.getItems().setAll(animalService.getCorrespondingAnimals());
        } catch (NoSuchEntityException e) {
            AlertBox.display("ERROR", e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    public void create(){
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(CreateAnimalController.class);
    }

    public void update(){
        AnimalDTO animalDTO = table.getSelectionModel().getSelectedItem();
        if(animalDTO == null){
            AlertBox.display("ERROR", "An animal must be selected");
            return;
        }
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(UpdateAnimalController.class, animalDTO);
    }

    public void delete(){
        AnimalDTO animalDTO = table.getSelectionModel().getSelectedItem();
        if(animalDTO == null){
            AlertBox.display("ERROR", "An animal must be selected");
            return;
        }
        animalService.removeById(animalDTO.getAnimalId());
        refresh();
    }

    public void view(){
        AnimalDTO animalDTO = table.getSelectionModel().getSelectedItem();
        if(animalDTO == null){
            AlertBox.display("ERROR", "An animal must be selected");
            return;
        }
        JavaFXApplication.changeScene(ViewAnimalController.class,animalDTO);
    }

}
