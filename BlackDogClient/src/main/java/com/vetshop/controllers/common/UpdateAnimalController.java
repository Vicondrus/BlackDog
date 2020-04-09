package com.vetshop.controllers.common;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.DTOController;
import com.vetshop.dtos.AnimalDTO;
import com.vetshop.dtos.DTO;
import com.vetshop.services.AnimalService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("updateanimal-stage.fxml")
public class UpdateAnimalController implements DTOController {

    private AnimalDTO animalDTO;

    @Autowired
    private AnimalService animalService;

    @FXML
    private TextField name;

    @FXML
    private TextField owner;

    @FXML
    private TextField species;

    @Override
    public void refresh(){
        name.setText(animalDTO.getName());
        owner.setText(animalDTO.getOwner());
        species.setText(animalDTO.getSpecies());
    }

    @Override
    public void setDTO(DTO dto) {
        animalDTO = (AnimalDTO) dto;
    }

    public void update(){
        animalService.postUpdateAnimal(animalDTO.getAnimalId(), name.getText(), owner.getText(), species.getText());

        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(InspectAnimalsController.class);
    }
}
