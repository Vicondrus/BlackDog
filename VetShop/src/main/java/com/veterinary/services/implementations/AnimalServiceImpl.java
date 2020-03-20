package com.veterinary.services.implementations;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.entities.Animal;
import com.veterinary.entities.RegularUser;
import com.veterinary.entities.UserType;
import com.veterinary.repositories.AnimalRepository;
import com.veterinary.repositories.RegularUserRepository;
import com.veterinary.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepo;

    @Autowired
    private RegularUserRepository regularUserRepository;

    @Override
    public List<AnimalDTO> getAll() {
        return animalRepo.findAll().stream().map(AnimalDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AnimalDTO> getAllAnimalsConsultedBy(RegularUser regularUser){
        RegularUser user = regularUserRepository.findById(regularUser.getIdUser()).orElse(null);
        if(user == null) {
            return null;
        }
        return user.getConsultedAnimals().stream().map(AnimalDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AnimalDTO> getCorrespondingAnimals() {
        RegularUser principal = (RegularUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal.getUserType().equals(UserType.ADMIN)) {
            return getAll();
        }
        else{
            return getAllAnimalsConsultedBy(principal);
        }
    }

    @Override
    public AnimalDTO save(String name, String owner, String species) {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setOwner(owner);
        animal.setSpecies(species);
        return new AnimalDTO(animalRepo.save(animal));
    }

    @Override
    public AnimalDTO update(int id, String name, String owner, String species) {
        return null;
    }

    @Override
    public AnimalDTO removeById(int id) {
        return null;
    }

    @Override
    public AnimalDTO getByName(String name) {
        return new AnimalDTO(animalRepo.findByName(name));
    }
}
