package com.vetshop.controllers;

import com.vetshop.dtos.ItemDTO;
import com.vetshop.dtos.ItemListWrapperDTO;
import com.vetshop.dtos.StringsListWrapperDTO;
import com.vetshop.services.ItemService;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * The type Item controller.
 */
@RestController
public class ItemController {

    private final ItemService itemService;

    /**
     * Instantiates a new Item controller.
     *
     * @param itemService the item service
     */
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Create item item dto.
     *
     * @param itemDTO the item dto
     * @return the item dto
     */
    @PostMapping(value = "/createItem")
    public ItemDTO createItem(@RequestBody ItemDTO itemDTO) {
        return itemService.createItem(itemDTO.getName(), itemDTO.getQuantity());
    }

    /**
     * Gets all items.
     *
     * @return the all items
     */
    @GetMapping(value = "/getAllItems")
    public ItemListWrapperDTO getAllItems() {
        return ItemListWrapperDTO.builder().list(itemService.getAllItems()).build();
    }

    /**
     * Gets all items names.
     *
     * @return the all items names
     */
    @GetMapping(value = "/getAllItemsNames")
    public StringsListWrapperDTO getAllItemsNames() {
        return StringsListWrapperDTO.builder().strings(itemService.getAllItems().stream().map(ItemDTO::getName).collect(Collectors.toList())).build();
    }

    /**
     * Delete item item dto.
     *
     * @param itemDTO the item dto
     * @return the item dto
     * @throws NoSuchEntityException the no such entity exception
     */
    @PostMapping(value = "/deleteItem")
    public ItemDTO deleteItem(@RequestBody ItemDTO itemDTO) throws NoSuchEntityException {
        return itemService.deleteItem(itemDTO.getItemId());
    }

    /**
     * Gets all items.
     *
     * @param itemDTO the item dto
     * @return the all items
     * @throws NoSuchEntityException the no such entity exception
     */
    @PostMapping(value = "/updateItem")
    public ItemDTO getAllItems(@RequestBody ItemDTO itemDTO) throws NoSuchEntityException {
        return itemService.updateItem(itemDTO.getItemId(), itemDTO.getName(), itemDTO.getQuantity() + "");
    }

}
