package com.vetshop.repositories;

import com.vetshop.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Animal repository.
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    /**
     * Find by name animal.
     *
     * @param name the name
     * @return the animal
     */
    Animal findByName(String name);

}
