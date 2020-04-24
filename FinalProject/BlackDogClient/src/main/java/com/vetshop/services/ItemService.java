package com.vetshop.services;

import com.vetshop.dtos.ItemDTO;
import com.vetshop.exceptions.FieldException;

import java.util.List;

/**
 * The interface Item service.
 */
public interface ItemService {

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

    /**
     * Post create item item dto.
     *
     * @param name     the name
     * @param quantity the quantity
     * @return the item dto
     * @throws FieldException the field exception
     */
    ItemDTO postCreateItem(String name, String quantity) throws FieldException;

    /**
     * Delete item item dto.
     *
     * @param item the item
     * @return the item dto
     */
    ItemDTO deleteItem(ItemDTO item);

    /**
     * Post update item item dto.
     *
     * @param itemId the item id
     * @param text   the text
     * @param text1  the text 1
     * @return the item dto
     * @throws FieldException the field exception
     */
    ItemDTO postUpdateItem(int itemId, String text, String text1) throws FieldException;
}
