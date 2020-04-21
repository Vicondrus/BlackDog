package com.vetshop.repositories;

import com.vetshop.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Consultation repository.
 */
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    /**
     * Find by doctor username list.
     *
     * @param username the username
     * @return the list
     */
    List<Consultation> findByDoctorUsername(String username);

}
