package com.vetshop.controllers;

import com.vetshop.dtos.AnimalDTO;
import com.vetshop.dtos.AnimalsListWrapperDTO;
import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.services.AnimalService;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Animal controller.
 */
@RestController
public class AnimalController {

    private final AnimalService animalService;

    /**
     * Instantiates a new Animal controller.
     *
     * @param animalService the animal service
     */
    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * Gets all animals.
     *
     * @param regularUserDTO the regular user dto
     * @return the all animals
     * @throws NoSuchEntityException the no such entity exception
     */
    @PostMapping(value = "/getAllAnimals")
    public AnimalsListWrapperDTO getAllAnimals(@RequestBody RegularUserDTO regularUserDTO) throws NoSuchEntityException {
        return AnimalsListWrapperDTO.builder().list(animalService.getCorrespondingAnimals(regularUserDTO)).build();
    }

    /**
     * Delete animal animal dto.
     *
     * @param animalDTO the animal dto
     * @return the animal dto
     */
    @PostMapping(value = "/deleteAnimal")
    public AnimalDTO deleteAnimal(@RequestBody AnimalDTO animalDTO) {
        return animalService.removeById(animalDTO.getAnimalId());
    }

    /**
     * Create animal animal dto.
     *
     * @param animalDTO the animal dto
     * @return the animal dto
     */
    @PostMapping(value = "/createAnimal")
    public AnimalDTO createAnimal(@RequestBody AnimalDTO animalDTO) {
        return animalService.save(animalDTO.getName(), animalDTO.getOwner(), animalDTO.getSpecies());
    }

    /**
     * Update animal animal dto.
     *
     * @param animalDTO the animal dto
     * @return the animal dto
     */
    @PostMapping(value = "/updateAnimal")
    public AnimalDTO updateAnimal(@RequestBody AnimalDTO animalDTO) {
        AnimalDTO animal = null;
        try {
            animal = animalService.update(animalDTO.getAnimalId(), animalDTO.getName(), animalDTO.getOwner(), animalDTO.getSpecies());
        } catch (NoSuchEntityException e) {
            e.printStackTrace();
        }
        return animal;
    }

}
