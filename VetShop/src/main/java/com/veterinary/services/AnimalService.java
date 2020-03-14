package com.veterinary.services;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.entities.Animal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimalService {

    List<AnimalDTO> getAll();

    AnimalDTO save(String name, String owner, String species);

    AnimalDTO update(int id, String name, String owner, String species);

    AnimalDTO removeById(int id);

    AnimalDTO getByName(String name);

}
