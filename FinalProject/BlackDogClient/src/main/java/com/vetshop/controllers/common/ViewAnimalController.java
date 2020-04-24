package com.vetshop.controllers.common;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.DTOController;
import com.vetshop.controllers.user.InspectConsultationsController;
import com.vetshop.dtos.AnimalDTO;
import com.vetshop.dtos.DTO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 * The type View animal controller.
 */
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
    public void refresh() {
        name.setText(animalDTO.getName());
        owner.setText(animalDTO.getOwner());
        species.setText(animalDTO.getSpecies());
    }

    @Override
    public void setDTO(DTO dto) {
        animalDTO = (AnimalDTO) dto;
    }

    /**
     * Back.
     */
    public void back() {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectConsultationsController.class);
    }
}
