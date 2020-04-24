package com.vetshop.controllers.admin;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.DTOController;
import com.vetshop.dtos.DTO;
import com.vetshop.dtos.ItemDTO;
import com.vetshop.exceptions.FieldException;
import com.vetshop.services.ItemService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 * The type Update item controller.
 */
@Component
@FxmlView("updateitem-stage.fxml")
public class UpdateItemController implements DTOController {

    private final ItemService itemService;
    private ItemDTO item;
    @FXML
    private TextField name;

    @FXML
    private TextField stock;

    /**
     * Instantiates a new Update item controller.
     *
     * @param itemService the item service
     */
    public UpdateItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void setDTO(DTO dto) {
        item = (ItemDTO) dto;
    }

    @Override
    public void refresh() {
        name.setText(item.getName());
        stock.setText(item.getQuantity() + "");
    }

    /**
     * Back.
     */
    public void back() {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(InspectItemsController.class);
    }

    /**
     * Update.
     */
    public void update() {
        try {
            itemService.postUpdateItem(item.getItemId(), name.getText(), stock.getText());
        } catch (FieldException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(InspectItemsController.class);
    }
}
