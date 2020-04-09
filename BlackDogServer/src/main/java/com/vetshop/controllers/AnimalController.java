package com.vetshop.controllers;

import com.vetshop.dtos.*;
import com.vetshop.entities.Animal;
import com.vetshop.services.AnimalService;
import com.vetshop.services.RegularUserService;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping(value = "/getAllAnimals")
    public AnimalsListWrapperDTO getAllAnimals() {
        return AnimalsListWrapperDTO.builder().list(animalService.getAll()).build();
    }

    @PostMapping(value = "/deleteAnimal")
    public AnimalDTO deleteAnimal(@RequestBody AnimalDTO animalDTO) {
        return animalService.removeById(animalDTO.getAnimalId());
    }

    @PostMapping(value = "/createAnimal")
    public AnimalDTO createAnimal(@RequestBody AnimalDTO animalDTO) {
        return animalService.save(animalDTO.getName(), animalDTO.getOwner(), animalDTO.getSpecies());
    }

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
