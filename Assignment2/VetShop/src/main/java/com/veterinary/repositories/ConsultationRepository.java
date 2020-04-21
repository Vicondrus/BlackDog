package com.veterinary.repositories;

import com.veterinary.entities.Consultation;
import com.veterinary.entities.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    List<Consultation> findByDoctor(RegularUser user);

}
