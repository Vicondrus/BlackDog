package com.vetshop.services;

import com.vetshop.dtos.AnimalDTO;
import com.vetshop.dtos.RegularUserDTO;
import com.vetshop.entities.RegularUser;
import com.vetshop.services.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The interface Animal service.
 */
@Service
public interface AnimalService {

    /**
     * Gets all.
     *
     * @return the all
     */
    List<AnimalDTO> getAll();

    /**
     * Save animal dto.
     *
     * @param name    the name
     * @param owner   the owner
     * @param species the species
     * @return the animal dto
     */
    AnimalDTO save(String name, String owner, String species);

    /**
     * Update animal dto.
     *
     * @param id      the id
     * @param name    the name
     * @param owner   the owner
     * @param species the species
     * @return the animal dto
     * @throws NoSuchEntityException the no such entity exception
     */
    AnimalDTO update(int id, String name, String owner, String species) throws NoSuchEntityException;

    /**
     * Remove by id animal dto.
     *
     * @param id the id
     * @return the animal dto
     */
    AnimalDTO removeById(int id);

    /**
     * Gets all animals consulted by.
     *
     * @param regularUser the regular user
     * @return the all animals consulted by
     * @throws NoSuchEntityException the no such entity exception
     */
    List<AnimalDTO> getAllAnimalsConsultedBy(RegularUser regularUser) throws NoSuchEntityException;

    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     */
    AnimalDTO getByName(String name);

    /**
     * Gets corresponding animals.
     *
     * @param regularUserDTO the regular user dto
     * @return the corresponding animals
     * @throws NoSuchEntityException the no such entity exception
     */
    List<AnimalDTO> getCorrespondingAnimals(RegularUserDTO regularUserDTO) throws NoSuchEntityException;

}
