package com.vetshop.dtos;

import com.vetshop.entities.Animal;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * The type Animal dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
@ToString
@Getter
@Setter
@Data
public class AnimalDTO implements DTO {

    private int animalId;

    private String name;

    private String owner;

    private String species;

    /**
     * Instantiates a new Animal dto.
     *
     * @param animal the animal
     */
    public AnimalDTO(Animal animal) {
        this.animalId = animal.getAnimalId();
        this.name = animal.getName();
        this.owner = animal.getOwner();
        this.species = animal.getSpecies();
    }
}
