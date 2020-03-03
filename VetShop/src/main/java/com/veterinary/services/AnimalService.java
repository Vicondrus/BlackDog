package com.veterinary.services;

import com.veterinary.entities.Animal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimalService {

    List<Animal> getAll();

    Animal save(Animal animal);

    Animal update(Animal animal);

    Animal remove(Animal animal);

}
