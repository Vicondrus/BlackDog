package com.veterinary.services;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.entities.Animal;
import com.veterinary.entities.RegularUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimalService {

    List<AnimalDTO> getAll();

    AnimalDTO save(String name, String owner, String species);

    AnimalDTO update(int id, String name, String owner, String species);

    AnimalDTO removeById(int id);

    List<AnimalDTO> getAllAnimalsConsultedBy(RegularUser regularUser);

    List<AnimalDTO> getCorrespondingAnimals();

    AnimalDTO getByName(String name);

}
