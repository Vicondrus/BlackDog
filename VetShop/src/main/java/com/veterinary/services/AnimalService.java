package com.veterinary.services;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.entities.Animal;
import com.veterinary.entities.RegularUser;
import com.veterinary.services.exceptions.FieldException;
import com.veterinary.services.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimalService {

    List<AnimalDTO> getAll();

    AnimalDTO save(String name, String owner, String species) throws FieldException;

    AnimalDTO update(int id, String name, String owner, String species) throws NoSuchEntityException, FieldException;

    AnimalDTO removeById(int id);

    List<AnimalDTO> getAllAnimalsConsultedBy(RegularUser regularUser) throws NoSuchEntityException;

    List<AnimalDTO> getCorrespondingAnimals() throws NoSuchEntityException;

    AnimalDTO getByName(String name);

}
