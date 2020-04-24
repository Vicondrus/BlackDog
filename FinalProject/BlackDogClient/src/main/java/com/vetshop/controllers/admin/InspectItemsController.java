package com.vetshop.controllers.admin;

import com.vetshop.application.JavaFXApplication;
import com.vetshop.controllers.Controller;
import com.vetshop.dialogues.AlertBox;
import com.vetshop.dtos.ItemDTO;
import com.vetshop.services.ItemService;
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
 * The type Inspect items controller.
 */
@Component
@FxmlView("inspectitems-stage.fxml")
public class InspectItemsController implements Controller, Initializable {

    private final ItemService itemService;

    @FXML
    private TableView<ItemDTO> table;

    @FXML
    private TableColumn<ItemDTO, String> item;

    @FXML
    private TableColumn<ItemDTO, Integer> stock;

    /**
     * Instantiates a new Inspect items controller.
     *
     * @param itemService the item service
     */
    public InspectItemsController(ItemService itemService) {
        this.itemService = itemService;
    }


    /**
     * Create.
     */
    public void create() {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(CreateItemController.class);
    }

    @Override
    public void refresh() {
        item.setCellValueFactory(new PropertyValueFactory<ItemDTO, String>("name"));
        stock.setCellValueFactory(new PropertyValueFactory<ItemDTO, Integer>("quantity"));

        List<ItemDTO> list = itemService.getAllItems();

        table.getItems().setAll(list);
    }

    /**
     * Update.
     */
    public void update() {
        ItemDTO itemDTO = table.getSelectionModel().getSelectedItem();
        if (itemDTO == null) {
            AlertBox.display("ERROR", "A user must be selected");
            return;
        }
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
        JavaFXApplication.changeScene(UpdateItemController.class, itemDTO);
    }

    /**
     * Delete.
     */
    public void delete() {
        ItemDTO itemDTO = table.getSelectionModel().getSelectedItem();
        if (itemDTO == null) {
            AlertBox.display("ERROR", "A user must be selected");
            return;
        }
        itemService.deleteItem(itemDTO);
        refresh();
    }

    /**
     * Back.
     */
    public void back() {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();

        JavaFXApplication.changeScene(AdminUserController.class);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }
}
