package com.vetshop.services;

import com.vetshop.dtos.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AnimalService {

    public List<AnimalDTO> findAllAnimals(){
        final String uri = "http://localhost:8080/getAllAnimals";

        RestTemplate restTemplate = new RestTemplate();

        AnimalsListWrapperDTO consultations = restTemplate.getForObject(uri, AnimalsListWrapperDTO.class);

        return consultations.getList();
    }

}
