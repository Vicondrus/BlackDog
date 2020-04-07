package com.vetshop.services;

import com.vetshop.dtos.AnimalDTO;
import com.vetshop.entities.RegularUser;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimalService {

    List<AnimalDTO> getAll();

    AnimalDTO save(String name, String owner, String species);

    AnimalDTO update(int id, String name, String owner, String species) throws NoSuchEntityException;

    AnimalDTO removeById(int id);

    List<AnimalDTO> getAllAnimalsConsultedBy(RegularUser regularUser) throws NoSuchEntityException;

    List<AnimalDTO> getCorrespondingAnimals() throws NoSuchEntityException;

    AnimalDTO getByName(String name);

}
