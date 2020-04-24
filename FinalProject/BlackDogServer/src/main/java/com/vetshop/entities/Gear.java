package com.vetshop.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * The type Gear.
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Getter
@Setter
@Entity
public class Gear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    private double quantity;

    /**
     * Gets item stock.
     *
     * @return the item stock
     */
    public double getItemStock() {
        return item.getStock();
    }

}
