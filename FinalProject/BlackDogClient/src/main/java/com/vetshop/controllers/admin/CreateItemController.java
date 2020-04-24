package com.vetshop.controllers.admin;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.exceptions.FieldException;
import com.vetshop.services.ItemService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 * The type Create item controller.
 */
@Component
@FxmlView("createitem-stage.fxml")
public class CreateItemController implements Controller {

    private final ItemService itemService;

    @FXML
    private TextField name;

    @FXML
    private TextField stock;

    /**
     * Instantiates a new Create item controller.
     *
     * @param itemService the item service
     */
    public CreateItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Back.
     */
    public void back() {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectUsersController.class);
    }

    /**
     * Create.
     */
    public void create() {
        try {
            itemService.postCreateItem(name.getText(), stock.getText());
            Stage stage = (Stage) name.getScene().getWindow();
            stage.close();

            JavaFXApplication.changeScene(InspectItemsController.class);
        } catch (FieldException e) {
            AlertBox.display("ERROR", e.getMessage());
        }
    }
}
