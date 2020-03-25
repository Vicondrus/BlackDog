package com.veterinary.controllers.common_controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.controllers.DTOController;
import com.veterinary.dtos.AnimalDTO;
import com.veterinary.dtos.DTO;
import com.veterinary.services.AnimalService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("viewanimal-stage.fxml")
public class ViewAnimalController implements DTOController {

    private AnimalDTO animalDTO;

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
}
