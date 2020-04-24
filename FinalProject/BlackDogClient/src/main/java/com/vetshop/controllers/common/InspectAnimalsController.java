package com.vetshop.controllers.common;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.controllers.admin.AdminUserController;
import com.vetshop.controllers.user.RegularUserController;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.AnimalDTO;
import com.vetshop.dtos.TypeDTO;
import com.vetshop.services.AnimalService;
import com.vetshop.services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The type Inspect animals controller.
 */
@Component
@FxmlView("inspectanimals-stage.fxml")
public class InspectAnimalsController implements Controller, Initializable {

    private final AnimalService animalService;

    private final AuthService authService;

    @FXML
    private TableView<AnimalDTO> table;

    @FXML
    private TableColumn<AnimalDTO, String> name;

    @FXML
    private TableColumn<AnimalDTO, String> owner;

    @FXML
    private TableColumn<AnimalDTO, String> species;

    /**
     * Instantiates a new Inspect animals controller.
     *
     * @param animalService the animal service
     * @param authService   the auth service
     */
    public InspectAnimalsController(AnimalService animalService, AuthService authService) {
        this.animalService = animalService;
        this.authService = authService;
    }

    @Override
    public void refresh() {
        name.setCellValueFactory(new PropertyValueFactory<AnimalDTO, String>("name"));
        owner.setCellValueFactory(new PropertyValueFactory<AnimalDTO, String>("owner"));
        species.setCellValueFactory(new PropertyValueFactory<AnimalDTO, String>("species"));

        List<AnimalDTO> list = animalService.findAllAnimals();

        table.getItems().setAll(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }

    /**
     * Create.
     */
    public void create() {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(CreateAnimalController.class);
    }

    /**
     * Update.
     */
    public void update() {
        AnimalDTO animalDTO = table.getSelectionModel().getSelectedItem();
        if (animalDTO == null) {
            AlertBox.display("ERROR", "An animal must be selected");
            return;
        }
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(UpdateAnimalController.class, animalDTO);
    }

    /**
     * Delete.
     */
    public void delete() {
        AnimalDTO animalDTO = table.getSelectionModel().getSelectedItem();
        if (animalDTO == null) {
            AlertBox.display("ERROR", "An animal must be selected");
            return;
        }
        animalService.deleteAnimal(animalDTO);
        refresh();
    }

    /**
     * View.
     */
    public void view() {
        AnimalDTO animalDTO = table.getSelectionModel().getSelectedItem();
        if (animalDTO == null) {
            AlertBox.display("ERROR", "An animal must be selected");
            return;
        }
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(ViewAnimalController.class, animalDTO);
    }

    /**
     * Back.
     */
    public void back() {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();

        if (authService.getUserType().equals(TypeDTO.REGULAR))
            JavaFXApplication.changeScene(RegularUserController.class);
        else
            JavaFXApplication.changeScene(AdminUserController.class);
    }
}
