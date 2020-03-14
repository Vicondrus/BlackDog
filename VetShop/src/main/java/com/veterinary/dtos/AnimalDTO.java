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
public class AnimalDTO {

    private int animalId;

    private String name;

    private String owner;

    private String species;

    private List<ConsultationDTO> consultations;

    public AnimalDTO(Animal animal) {
        this.animalId = animal.getAnimalId();
        this.name = animal.getName();
        this.owner = animal.getOwner();
        this.species = animal.getSpecies();
        consultations = animal.getConsultationList().stream().map(ConsultationDTO::new).collect(Collectors.toList());
    }
}
