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
import org.springframework.stereotype.Component;

/**
 * The type Update animal controller.
 */
@Component
@FxmlView("updateanimal-stage.fxml")
public class UpdateAnimalController implements DTOController {

    private final AnimalService animalService;
    private AnimalDTO animalDTO;
    @FXML
    private TextField name;

    @FXML
    private TextField owner;

    @FXML
    private TextField species;

    /**
     * Instantiates a new Update animal controller.
     *
     * @param animalService the animal service
     */
    public UpdateAnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

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
     * Update.
     */
    public void update() {
        animalService.postUpdateAnimal(animalDTO.getAnimalId(), name.getText(), owner.getText(), species.getText());

        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(InspectAnimalsController.class);
    }

    /**
     * Back.
     */
    public void back() {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectAnimalsController.class);
    }
}
