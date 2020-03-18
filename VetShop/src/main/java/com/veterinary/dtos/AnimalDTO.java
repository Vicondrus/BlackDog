package com.veterinary.dtos;

import com.veterinary.entities.Animal;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
@ToString
@Getter
@Setter
@Data
public class AnimalDTO implements DTO{

    private int animalId;

    private String name;

    private String owner;

    private String species;

    public AnimalDTO(Animal animal) {
        this.animalId = animal.getAnimalId();
        this.name = animal.getName();
        this.owner = animal.getOwner();
        this.species = animal.getSpecies();
    }
}
