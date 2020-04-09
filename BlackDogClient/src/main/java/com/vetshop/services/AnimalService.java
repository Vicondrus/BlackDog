package com.vetshop.services;

import com.vetshop.dtos.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AnimalService {

    public List<AnimalDTO> findAllAnimals(){
        final String uri = "http://localhost:8080/getAllAnimals";

        RestTemplate restTemplate = new RestTemplate();

        AnimalsListWrapperDTO consultations = restTemplate.getForObject(uri, AnimalsListWrapperDTO.class);

        return consultations.getList();
    }

    public AnimalDTO deleteAnimal(AnimalDTO animalDTO){
        final String uri = "http://localhost:8080/deleteAnimal";

        RestTemplate restTemplate = new RestTemplate();
        animalDTO = restTemplate.postForObject(uri, animalDTO, AnimalDTO.class);
        return animalDTO;
    }

    public AnimalDTO postCreateAnimal(String name, String owner, String species){
        final String uri = "http://localhost:8080/createAnimal";

        RestTemplate restTemplate = new RestTemplate();

        AnimalDTO animal = AnimalDTO.builder().name(name).owner(owner).species(species).build();

        animal = restTemplate.postForObject(uri, animal, AnimalDTO.class);

        return animal;
    }

    public AnimalDTO postUpdateAnimal(int id, String name, String owner, String species){
        final String uri = "http://localhost:8080/updateAnimal";

        RestTemplate restTemplate = new RestTemplate();

        AnimalDTO animal = AnimalDTO.builder().animalId(id).name(name).owner(owner).species(species).build();

        animal = restTemplate.postForObject(uri, animal, AnimalDTO.class);

        return animal;
    }

}
