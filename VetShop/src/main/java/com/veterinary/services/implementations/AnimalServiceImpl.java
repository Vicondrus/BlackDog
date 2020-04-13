package com.veterinary.services.implementations;

import com.veterinary.dtos.AnimalDTO;
import com.veterinary.entities.Animal;
import com.veterinary.entities.RegularUser;
import com.veterinary.entities.User;
import com.veterinary.entities.UserType;
import com.veterinary.repositories.AnimalRepository;
import com.veterinary.repositories.RegularUserRepository;
import com.veterinary.services.AnimalService;
import com.veterinary.services.exceptions.FieldException;
import com.veterinary.services.exceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepo;

    private RegularUserRepository regularUserRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository ar, RegularUserRepository rur){
        animalRepo = ar;
        regularUserRepository = rur;
    }

    @Override
    public List<AnimalDTO> getAll() {
        return animalRepo.findAll().stream().map(AnimalDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AnimalDTO> getAllAnimalsConsultedBy(RegularUser regularUser) throws NoSuchEntityException {
        RegularUser user = regularUserRepository.findById(regularUser.getIdUser()).orElse(null);
        if(user == null) {
            throw new NoSuchEntityException("No such user");
        }
        return user.getConsultedAnimals().stream().map(AnimalDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AnimalDTO> getCorrespondingAnimals() throws NoSuchEntityException {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal.getUserType().equals(UserType.ADMIN)) {
            return getAll();
        }
        else{
            return getAllAnimalsConsultedBy((RegularUser) principal);
        }
    }

    @Override
    public AnimalDTO save(String name, String owner, String species) throws FieldException {
        if(name.equals(""))
            throw new FieldException("Animal name cannot be empty");
        if(owner.equals(""))
            throw new FieldException("Owner name cannot be empty");
        Animal animal = new Animal();
        animal.setName(name);
        animal.setOwner(owner);
        animal.setSpecies(species);
        return new AnimalDTO(animalRepo.save(animal));
    }

    @Override
    public AnimalDTO update(int id, String name, String owner, String species) throws NoSuchEntityException, FieldException {
        Animal animal = animalRepo.findById(id).orElse(null);
        if(name.equals(""))
            throw new FieldException("Animal name cannot be empty");
        if(owner.equals(""))
            throw new FieldException("Owner name cannot be empty");
        if(animal == null)
            throw new NoSuchEntityException("The animal doesn't exist");
        animal.setName(name);
        animal.setOwner(owner);
        animal.setSpecies(species);
        return new AnimalDTO(animalRepo.save(animal));
    }

    @Override
    public AnimalDTO removeById(int id) {
        Animal animal = animalRepo.findById(id).orElse(null);
        animalRepo.delete(animal);
        return new AnimalDTO(animal);
    }

    @Override
    public AnimalDTO getByName(String name) {
        return new AnimalDTO(animalRepo.findByName(name));
    }
}
