package com.vetshop.repositories;

import com.vetshop.entities.Consultation;
import com.vetshop.entities.Gear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Gear repository.
 */
@Repository
public interface GearRepository extends JpaRepository<Gear, Integer> {

    /**
     * Gets all by consultation.
     *
     * @param consultation the consultation
     * @return the all by consultation
     */
    List<Gear> getAllByConsultation(Consultation consultation);

}
