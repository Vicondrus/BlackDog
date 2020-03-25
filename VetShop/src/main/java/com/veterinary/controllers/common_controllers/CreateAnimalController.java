package com.veterinary.controllers.common_controllers;

import com.veterinary.application.JavaFXApplication;
import com.veterinary.controllers.Controller;
import com.veterinary.services.AnimalService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void create(){

        animalService.save(name.getText(), owner.getText(), species.getText());

        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(InspectAnimalsController.class);
    }

}
