package com.vetshop.entities;

import com.vetshop.dtos.ItemDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Item.
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "neededIn")
@SuperBuilder
@Getter
@Setter
@Entity
public class Item {

    /**
     * The Needed in.
     */
    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    List<Gear> neededIn = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String name;
    private double stock;

    /**
     * Instantiates a new Item.
     *
     * @param item the item
     */
    public Item(ItemDTO item) {
        itemId = item.getItemId();
        name = item.getName();
        stock = item.getQuantity();
    }

    /**
     * Add gear.
     *
     * @param g the g
     */
    public void addGear(Gear g) {
        neededIn.add(g);
    }
}
