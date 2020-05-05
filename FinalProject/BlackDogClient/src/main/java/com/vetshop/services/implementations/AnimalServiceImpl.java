package com.vetshop.services.implementations;

import com.vetshop.dtos.AnimalDTO;
import com.vetshop.dtos.AnimalsListWrapperDTO;
import com.vetshop.dtos.UserDTO;
import com.vetshop.services.AnimalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The type Animal service.
 */
@Service
public class AnimalServiceImpl implements AnimalService {

    private final String uriRoot;

    /**
     * Instantiates a new Animal service.
     *
     * @param hostAndPort the host and port
     */
    public AnimalServiceImpl(@Value("${server.host-and-port}") String hostAndPort) {
        this.uriRoot = "http://" + hostAndPort;
    }

    @Override
    public List<AnimalDTO> findAllAnimals() {
        final String uri = uriRoot + "/getAllAnimals";

        RestTemplate restTemplate = new RestTemplate();

        UserDTO principal = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AnimalsListWrapperDTO consultations = restTemplate.postForObject(uri, principal, AnimalsListWrapperDTO.class);

        return consultations.getList();
    }

    @Override
    public AnimalDTO deleteAnimal(AnimalDTO animalDTO) {
        final String uri = uriRoot + "/deleteAnimal";

        RestTemplate restTemplate = new RestTemplate();
        animalDTO = restTemplate.postForObject(uri, animalDTO, AnimalDTO.class);
        return animalDTO;
    }

    @Override
    public AnimalDTO postCreateAnimal(String name, String owner, String species) {
        final String uri = uriRoot + "/createAnimal";

        RestTemplate restTemplate = new RestTemplate();

        AnimalDTO animal = AnimalDTO.builder().name(name).owner(owner).species(species).build();

        animal = restTemplate.postForObject(uri, animal, AnimalDTO.class);

        return animal;
    }

    @Override
    public AnimalDTO postUpdateAnimal(int id, String name, String owner, String species) {
        final String uri = uriRoot + "/updateAnimal";

        RestTemplate restTemplate = new RestTemplate();

        AnimalDTO animal = AnimalDTO.builder().animalId(id).name(name).owner(owner).species(species).build();

        animal = restTemplate.postForObject(uri, animal, AnimalDTO.class);

        return animal;
    }

}
