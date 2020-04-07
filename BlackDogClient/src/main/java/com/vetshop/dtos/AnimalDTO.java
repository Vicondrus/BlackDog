package com.vetshop.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

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
}
