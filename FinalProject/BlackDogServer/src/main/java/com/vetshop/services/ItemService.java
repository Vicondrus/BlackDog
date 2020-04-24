package com.vetshop.services;

import com.vetshop.dtos.ItemDTO;
import com.vetshop.services.exceptions.NoSuchEntityException;

import java.util.List;

/**
 * The interface Item service.
 */
public interface ItemService {

    /**
     * Create item item dto.
     *
     * @param name     the name
     * @param quantity the quantity
     * @return the item dto
     */
    ItemDTO createItem(String name, double quantity);

    /**
     * Update item item dto.
     *
     * @param id       the id
     * @param name     the name
     * @param quantity the quantity
     * @return the item dto
     * @throws NoSuchEntityException the no such entity exception
     */
    ItemDTO updateItem(int id, String name, String quantity) throws NoSuchEntityException;

    /**
     * Delete item item dto.
     *
     * @param id the id
     * @return the item dto
     * @throws NoSuchEntityException the no such entity exception
     */
    ItemDTO deleteItem(int id) throws NoSuchEntityException;

    /**
     * Gets all items names.
     *
     * @return the all items names
     */
    List<String> getAllItemsNames();

    /**
     * Gets all items.
     *
     * @return the all items
     */
    List<ItemDTO> getAllItems();
}
