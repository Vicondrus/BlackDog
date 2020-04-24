package com.vetshop.dtos;

import com.vetshop.entities.Item;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * The type Item dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
@ToString
@Getter
@Setter
@Data
public class ItemDTO {

    private int itemId;

    private String name;

    private double quantity;

    /**
     * Instantiates a new Item dto.
     *
     * @param item the item
     */
    public ItemDTO(Item item) {
        itemId = item.getItemId();
        this.name = item.getName();
        this.quantity = item.getStock();
    }

}
