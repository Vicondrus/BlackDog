package com.vetshop.services;

import com.vetshop.dtos.AnimalDTO;

import java.util.List;

/**
 * The interface Animal service.
 */
public interface AnimalService {
    /**
     * Find all animals list.
     *
     * @return the list
     */
    List<AnimalDTO> findAllAnimals();

    /**
     * Delete animal animal dto.
     *
     * @param animalDTO the animal dto
     * @return the animal dto
     */
    AnimalDTO deleteAnimal(AnimalDTO animalDTO);

    /**
     * Post create animal animal dto.
     *
     * @param name    the name
     * @param owner   the owner
     * @param species the species
     * @return the animal dto
     */
    AnimalDTO postCreateAnimal(String name, String owner, String species);

    /**
     * Post update animal animal dto.
     *
     * @param id      the id
     * @param name    the name
     * @param owner   the owner
     * @param species the species
     * @return the animal dto
     */
    AnimalDTO postUpdateAnimal(int id, String name, String owner, String species);
}
