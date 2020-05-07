package com.vetshop.controllers.common;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.services.AnimalService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Create animal controller.
 */
@Component
@FxmlView("createanimal-stage.fxml")
public class CreateAnimalController implements Controller {

    @Autowired
    private AnimalService animalService;

    @FXML
    private TextField name;

    @FXML
    private TextField owner;

    @FXML
    private TextField species;

    /**
     * Create.
     */
    public void create() {

        animalService.postCreateAnimal(name.getText(), owner.getText(), species.getText());

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
