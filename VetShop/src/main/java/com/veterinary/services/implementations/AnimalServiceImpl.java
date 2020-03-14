package com.veterinary.services.implementations;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.entities.Animal;
import com.veterinary.repositories.AnimalRepository;
import com.veterinary.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepo;

    @Override
    public List<AnimalDTO> getAll() {
        return animalRepo.findAll().stream().map(AnimalDTO::new).collect(Collectors.toList());
    }

    @Override
    public AnimalDTO save(String name, String owner) {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setOwner(owner);
        return new AnimalDTO(animalRepo.save(animal));
    }

    @Override
    public AnimalDTO update(Animal animal) {
        return null;
    }

    @Override
    public AnimalDTO remove(Animal animal) {
        return null;
    }

    @Override
    public AnimalDTO getByName(String name) {
        return new AnimalDTO(animalRepo.findByName(name));
    }
}
