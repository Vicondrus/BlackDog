package com.veterinary.services.implementations;

import com.veterinary.entities.Animal;
import com.veterinary.repositories.AnimalRepository;
import com.veterinary.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepo;

    @Override
    public List<Animal> getAll() {
        return animalRepo.findAll();
    }

    @Override
    public Animal save(Animal animal) {
        return animalRepo.save(animal);
    }

    @Override
    public Animal update(Animal animal) {
        return null;
    }

    @Override
    public Animal remove(Animal animal) {
        return null;
    }

    @Override
    public Animal getByName(String name) {
        return animalRepo.findByName(name);
    }
}
