package com.vetshop.services.implementations;

import com.vetshop.dtos.AnimalDTO;
import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.entities.Animal;
import com.vetshop.entities.RegularUser;
import com.vetshop.entities.User;
import com.vetshop.entities.UserType;
import com.vetshop.repositories.AnimalRepository;
import com.vetshop.repositories.RegularUserRepository;
import com.vetshop.repositories.UserRepository;
import com.vetshop.services.AnimalService;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Animal service.
 */
@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepo;

    private final RegularUserRepository regularUserRepository;

    private final UserRepository userRepository;

    /**
     * Instantiates a new Animal service.
     *
     * @param animalRepository      the animal repository
     * @param regularUserRepository the regular user repository
     * @param userRepository        the user repository
     */
    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, RegularUserRepository regularUserRepository, UserRepository userRepository) {
        this.animalRepo = animalRepository;
        this.regularUserRepository = regularUserRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<AnimalDTO> getAll() {
        return animalRepo.findAll().stream().map(AnimalDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AnimalDTO> getAllAnimalsConsultedBy(RegularUser regularUser) throws NoSuchEntityException {
        RegularUser user = regularUserRepository.findById(regularUser.getIdUser()).orElse(null);
        if (user == null) {
            throw new NoSuchEntityException("No such user");
        }
        return user.getConsultedAnimals().stream().map(AnimalDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<AnimalDTO> getCorrespondingAnimals(RegularUserDTO regularUserDTO) throws NoSuchEntityException {
        User principal = userRepository.findByUsername(regularUserDTO.getUsername());
        if (principal != null) {
            if (principal.getUserType().equals(UserType.ADMIN)) {
                return getAll();
            } else {
                return getAllAnimalsConsultedBy((RegularUser) principal);
            }
        } else {
            return null;
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
    public AnimalDTO update(int id, String name, String owner, String species) throws NoSuchEntityException {
        Animal animal = animalRepo.findById(id).orElse(null);
        if (animal == null)
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
